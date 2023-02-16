package com.allddaom.services.admin.banners.service;

import com.allddaom.commons.entity.BasePage;
import com.allddaom.commons.file.FileUploader;
import com.allddaom.models.banners.domain.Banner;
import com.allddaom.models.banners.domain.BannerStatus;
import com.allddaom.models.banners.service.BannerService;
import com.allddaom.services.admin.banners.dto.AdminBannerResponse;
import com.allddaom.services.admin.banners.dto.form.AdminBannerForm;
import com.allddaom.services.admin.banners.dto.search.AdminBannerSearchDto;
import com.allddaom.services.admin.banners.dto.search.AdminBannerSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminBannerService {

    private final BannerService bannerService;
    private final FileUploader fileUploader;

    public AdminBannerSearchDto.Response search(AdminBannerSearchDto.Request dto, Pageable pageable) {
        Page<Banner> bannerPage = bannerService.searchForAdmin(dto, pageable);
        Page<AdminBannerSearchResponse> bannerSearchResponsePage = bannerPage.map(AdminBannerSearchResponse::of);
        return AdminBannerSearchDto.Response.builder()
                .bannerPage(new BasePage<>(bannerSearchResponsePage))
                .build();
    }

    public AdminBannerForm getBannerForm(Long id) {
        Banner banner = bannerService.findById(id);
        return AdminBannerForm.of(banner);
    }

    public AdminBannerResponse add(AdminBannerForm dto) {
        String imageUrl = "";
        MultipartFile imageFile = dto.getImageFile();
        if (!ObjectUtils.isEmpty(imageFile) && !imageFile.isEmpty()) {
            // TODO 파일 업로드
            imageUrl = fileUploader.upload2(imageFile, "BANNERS");
        }
        Banner banner = dto.toEntity(imageUrl);

        banner = bannerService.add(banner);
        return AdminBannerResponse.of(banner);
    }

    public AdminBannerResponse update(AdminBannerForm dto) {
        Banner banner = bannerService.findById(dto.getId());
        String imageUrl = banner.getImageUrl();
        MultipartFile imageFile = dto.getImageFile();
        if (!ObjectUtils.isEmpty(imageFile) && !imageFile.isEmpty()) {
            // TODO 기존 이미지 삭제
            imageUrl = fileUploader.upload2(imageFile, "BANNERS");
        }
        banner.update(dto.getText1(), dto.getText2(), dto.getText3(), dto.getText4(), imageUrl, BannerStatus.getStringToEnum(dto.getStatus()));
        return AdminBannerResponse.of(banner);
    }
}
