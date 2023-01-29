package com.shop.services.admin.banners.service;

import com.shop.commons.entity.BasePage;
import com.shop.models.banners.domain.Banner;
import com.shop.models.banners.domain.BannerStatus;
import com.shop.models.banners.service.BannerService;
import com.shop.services.admin.banners.dto.AdminBannerResponse;
import com.shop.services.admin.banners.dto.form.AdminBannerForm;
import com.shop.services.admin.banners.dto.search.AdminBannerSearchDto;
import com.shop.services.admin.banners.dto.search.AdminBannerSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminBannerService {

    private final BannerService bannerService;

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
        Banner banner = dto.toEntity();
        String imageFile = dto.getImageFile();
        if (!ObjectUtils.isEmpty(imageFile)) {
            // TODO 파일 업로드
        }

        banner = bannerService.add(banner);
        return AdminBannerResponse.of(banner);
    }

    public AdminBannerResponse update(AdminBannerForm dto) {
        Banner banner = bannerService.findById(dto.getId());
        String imageUrl = dto.getImageUrl();
        String imageFile = dto.getImageFile();
        if (!ObjectUtils.isEmpty(imageFile)) {
            // TODO 파일 업로드
        }
        banner.update(dto.getText1(), dto.getText2(), dto.getText3(), dto.getText4(), imageUrl, BannerStatus.getStringToEnum(dto.getStatus()));
        return AdminBannerResponse.of(banner);
    }
}
