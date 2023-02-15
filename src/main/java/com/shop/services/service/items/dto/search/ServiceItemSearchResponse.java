package com.shop.services.service.items.dto.search;

import com.shop.models.items.domain.Item;
import com.shop.models.items.domain.ItemImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceItemSearchResponse {
    private Long id;
    private String name;
    private String brand;
    private String mainImageUrl;
    private Long salePrice; // 판매가
    private Long percent; // 판매가 / 정상가 * 100

    public static ServiceItemSearchResponse of(Item item) {
        ItemImage mainImage = item.getMainImage();
        return ServiceItemSearchResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .brand(item.getBrand())
                .salePrice(item.getSalePrice())
                .percent(item.getPercent())
                .mainImageUrl(ObjectUtils.isEmpty(mainImage) ? "" : mainImage.getUrl())
                .build();
    }
}
