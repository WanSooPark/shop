package com.allddaom.commons.file;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.allddaom.commons.errors.exceptions.InternalServerError;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Component
@Transactional
@RequiredArgsConstructor
public class AWSS3FileUploader implements FileUploader {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;  // S3 버킷 이름

    @Value("${upload_path}")
    private String localUploadPath;

    /**
     * MultipartFile 업로드 1
     */
    @Override
    public String upload(MultipartFile multipartFile, String objectType) {
        String imageUrl = "";
        File uploadFile;

        try {
            String originalFilename = multipartFile.getOriginalFilename(); // 원본 파일명
            String fileNameExtension = this.getFileNameExtension(originalFilename); // 원본파일명에서 확장자 추출
            String fileName = UUID.randomUUID() + fileNameExtension; // S3에 업로드할 파일명 (UUID + 확장자)

            String localPath = localUploadPath + this.getPathOfToday() + "/" + objectType; // 파일 저장 경로
            localPath = createDirectory(localPath); // 임시저장 로컬 폴더 없으면 생성
            uploadFile = saveToLocal(multipartFile.getBytes(), localPath, fileName);

            try {
                String s3UploadPath = "FILES" + this.getPathOfToday() + "/" + objectType;
                imageUrl = uploadToS3(uploadFile, s3UploadPath, fileName);
            } catch (Exception e) {
                throw new InternalServerError("AWS S3 Upload Exception " + e.getMessage());
            } finally {
                removeLocalFile(uploadFile);
            }
        } catch (IOException ioException) {
            throw new InternalServerError("IOException : " + ioException.getMessage());
        }

        return imageUrl;
    }

    /**
     * MultipartFile 업로드 2
     * 로컬 저장 없이 바로 s3 업로드
     */
    public String upload2(MultipartFile multipartFile, String objectType) {
        String imageUrl = "";

        String originalFilename = multipartFile.getOriginalFilename(); // 원본 파일명
        String fileNameExtension = this.getFileNameExtension(originalFilename); // 원본파일명에서 확장자 추출
        String fileName = UUID.randomUUID() + fileNameExtension; // S3에 업로드할 파일명 (UUID + 확장자)

        try {
            String s3UploadPath = "FILES" + this.getPathOfToday() + "/" + objectType;
            imageUrl = uploadToS3(multipartFile, s3UploadPath, fileName);
        } catch (Exception e) {
            throw new InternalServerError("AWS S3 Upload Exception " + e.getMessage());
        }

        return imageUrl;
    }

    /**
     * Base64 업로드
     */
    @Override
    public String upload(String base64, String objectType) {
        String imageUrl = "";
        File uploadFile;

        try {
            String[] fileData = base64.split(",");

            String mimeType = fileData[0];
            mimeType = mimeType.split(";")[0];
            String fileNameExtension = "." + mimeType.split("/")[1];

            String originBase64 = fileData[1];
            byte[] originBase64Bytes = DatatypeConverter.parseBase64Binary(originBase64);

            String fileName = UUID.randomUUID() + fileNameExtension; // S3에 업로드할 파일명 (UUID + 확장자)

            String localPath = localUploadPath + this.getPathOfToday() + "/" + objectType; // 파일 저장 경로
            localPath = createDirectory(localPath); // 임시저장 로컬 폴더 없으면 생성
            uploadFile = saveToLocal(originBase64Bytes, localPath, fileName);

            try {
                String s3UploadPath = "FILES" + this.getPathOfToday() + "/" + objectType;
                imageUrl = uploadToS3(uploadFile, s3UploadPath, fileName);
            } catch (Exception e) {
                throw new InternalServerError("AWS S3 Upload Exception " + e.getMessage());
            } finally {
                removeLocalFile(uploadFile);
            }
        } catch (IOException ioException) {
            throw new InternalServerError("IOException : " + ioException.getMessage());
        }

        return imageUrl;
    }

    @Override
    public String upload(File file, String path, String objectType) {
        String imageUrl = "";

        try {
            String fileName = file.getName(); // S3에 저장된 파일 이름
            imageUrl = uploadToS3(file, path, fileName); // s3로 업로드
        } catch (Exception e) {
            throw new InternalServerError("AWS S3 Upload Exception " + e.getMessage());
        } finally {
        }
        return imageUrl;
    }

    /**
     * 파일을 로컬에 저장
     */
    private File saveToLocal(byte[] bytes, String localPath, String fileName) throws IOException {
        File uploadFile;
        uploadFile = new File(localPath + "/" + fileName);
        if (uploadFile.createNewFile()) { // 바로 위에서 지정한 경로에 File이 생성됨 (경로가 잘못되었다면 생성 불가능)
            try (FileOutputStream fos = new FileOutputStream(uploadFile)) { // FileOutputStream 데이터를 파일에 바이트 스트림으로 저장하기 위함
                fos.write(bytes);
            }
        }
        return uploadFile;
    }

    /**
     * 로컬에 저장된 파일을 S3 에 업로드
     */
    private String uploadToS3(File uploadFile, String s3UploadPath, String fileName) {
        String imageUrl;
        amazonS3Client.putObject(new PutObjectRequest(bucket, s3UploadPath + "/" + fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        imageUrl = amazonS3Client.getUrl(bucket, s3UploadPath + "/" + fileName)
                .toString();
        return imageUrl;
    }

    /**
     * 파일을 바로 S3 에 업로드
     */
    private String uploadToS3(MultipartFile multipartFile, String s3UploadPath, String fileName) throws IOException {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(MediaType.ALL_VALUE);
        metadata.setContentLength(multipartFile.getSize());

        String imageUrl;
        amazonS3Client.putObject(new PutObjectRequest(bucket, s3UploadPath + "/" + fileName, multipartFile.getInputStream(), metadata).withCannedAcl(CannedAccessControlList.PublicRead));
        imageUrl = amazonS3Client.getUrl(bucket, s3UploadPath + "/" + fileName)
                .toString();
        return imageUrl;
    }

    /**
     * 파일 확장자 추출
     */
    private String getFileNameExtension(String originalFilename) {
        String[] split = Objects.requireNonNull(originalFilename)
                .split("\\.");
        String fileNameExtension = split[split.length - 1];
        return "." + fileNameExtension;
    }

    /**
     * 날짜 저장경로 설정
     */
    private String getPathOfToday() {
        LocalDateTime today = LocalDateTime.now();

        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();

        return "/" + year + "/" + month + "/" + day;
    }

    /**
     * 로컬 풀더 생성
     */
    private String createDirectory(String directory) throws IOException {
        Path path = Files.createDirectories(Path.of(directory));
        return path.toString();
    }

    /**
     * 로컬에 임시 저장한 파일 제거
     */
    private void removeLocalFile(File targetFile) {
        if (targetFile.delete()) {
        }
    }
}
