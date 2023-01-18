package com.shop.services.service.items.dto;

import com.shop.models.items.domain.Item;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceItemResponse {
    private Long id;
    private String name;
    private String basicDescription; // 기본설명
    private String brand; // 브랜드

    private Long regularPrice; // 정상가
    private Long salePrice; // 판매가
    private Long percent;

    public static ServiceItemResponse of(Item item) {
        return ServiceItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .brand(item.getBrand())
                .basicDescription(item.getBasicDescription())
                .regularPrice(item.getRegularPrice())
                .salePrice(item.getSalePrice())
                .percent(item.getPercent())
                .build();
    }
}
