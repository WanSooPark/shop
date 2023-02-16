package com.allddaom.services.admin.items.dto.search;

import com.allddaom.models.items.domain.Item;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@Data
@Builder
public class AdminItemSearchResponse {
    private Long id;
    private String name;
    private String brand;
    private String mainImageUrl;
    private Long regularPrice; // 정상가
    private Long salePrice; // 판매가
    private Long stock;
    private boolean saleStatus;
    private boolean productExposureStatus;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    public static AdminItemSearchResponse of(Item item) {
        return AdminItemSearchResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .brand(item.getBrand())
                .mainImageUrl(ObjectUtils.isEmpty(item.getMainImage()) ? "" : item.getMainImage()
                        .getUrl())
                .regularPrice(item.getRegularPrice())
                .salePrice(item.getSalePrice())
                .stock(item.getStock())
                .saleStatus(item.isSaleStatus())
                .productExposureStatus(item.isProductExposureStatus())
                .createdDateTime(item.getCreatedDateTime())
                .updatedDateTime(item.getUpdatedDateTime())
                .build();
    }
}
