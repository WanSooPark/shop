package com.shop.services.service.main.dto;

import com.shop.models.items.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MainItemResponse {
    private Long id;
    private String name;
    private String basicDescription;
    private Long regularPrice; // 정상가
    private Long salePrice; // 판매가
    private Long percent; // 판매가
    private boolean isZzim;

    public static MainItemResponse of(Item item) {
        return MainItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .basicDescription(item.getBasicDescription())
                .regularPrice(item.getRegularPrice())
                .salePrice(item.getSalePrice())
                .percent(item.getPercent())
                .isZzim(false) // TODO 찜
                .build();
    }
}
