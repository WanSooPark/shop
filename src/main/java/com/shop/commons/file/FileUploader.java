package com.shop.commons.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileUploader {
    String upload(MultipartFile multipartFile, String objectType);

    String upload2(MultipartFile multipartFile, String objectType);

    String upload(String base64, String objectType);

    String upload(File file, String path, String objectType);
}
