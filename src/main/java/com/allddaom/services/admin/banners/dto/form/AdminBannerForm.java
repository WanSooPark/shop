package com.allddaom.services.admin.banners.dto.form;

import com.allddaom.models.banners.domain.Banner;
import com.allddaom.models.banners.domain.BannerStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminBannerForm {

    private Long id;
    private String text1;
    private String text2;
    private String text3;
    private String text4;
    private String imageUrl;
    private MultipartFile imageFile;
    private String status;

    public static AdminBannerForm of(Banner banner) {
        return AdminBannerForm.builder()
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

    public static AdminBannerForm empty() {
        return AdminBannerForm.builder()
                .id(0L)
                .imageUrl("/img/visual_bg.png")
                .status(BannerStatus.USE.name())
                .build();
    }

    public Banner toEntity(String imageUrl) {
        Banner banner = new Banner();
        banner.setId(this.id);
        banner.setText1(this.text1);
        banner.setText2(this.text2);
        banner.setText3(this.text3);
        banner.setText4(this.text4);
        banner.setImageUrl(ObjectUtils.isEmpty(imageUrl) ? "/img/visual_bg.png" : imageUrl);
        banner.setStatus(BannerStatus.getStringToEnum(this.status));
        return banner;
    }
}
