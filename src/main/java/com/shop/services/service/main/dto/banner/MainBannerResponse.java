package com.shop.services.service.main.dto.banner;

import com.shop.models.banners.domain.Banner;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MainBannerResponse {
    private Long id;
    private String text1;
    private String text2;
    private String text3;
    private String text4;
    private String imageUrl;
    private String status;

    public static MainBannerResponse of(Banner banner) {
        return MainBannerResponse.builder()
                .id(banner.getId())
                .text1(banner.getText1())
                .text2(banner.getText2())
                .text3(banner.getText3())
                .text4(banner.getText4())
                .imageUrl(banner.getImageUrl())
                .status(banner.getStatus()
                        .name())
                .build();
    }
}
