package com.allddaom.services.service.items.dto;

import com.allddaom.models.items.domain.Item;
import com.allddaom.models.items.domain.ItemImage;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ServiceItemResponse {
    private Long id;
    private String name;
    private String basicDescription; // 기본설명
    private String brand; // 브랜드
    private String mainImageUrl; // 메인 이미지
    private List<String> imageUrls; // 이미지 목록

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
                .mainImageUrl(ObjectUtils.isEmpty(item.getMainImage()) ? "/img/detail.png" : item.getMainImage()
                        .getUrl())
                .imageUrls(ObjectUtils.isEmpty(item.getImages()) ? null : item.getImages()
                        .stream()
                        .map(ItemImage::getUrl)
                        .collect(Collectors.toList()))
                .build();
    }
}
