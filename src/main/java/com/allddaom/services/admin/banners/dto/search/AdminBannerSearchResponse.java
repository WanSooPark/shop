package com.allddaom.services.admin.banners.dto.search;

import com.allddaom.models.banners.domain.Banner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminBannerSearchResponse {

    private Long id;
    private String text1;
    private String text2;
    private String text3;
    private String text4;
    private String imageUrl;
    private String status;

    public static AdminBannerSearchResponse of(Banner banner) {
        return AdminBannerSearchResponse.builder()
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
