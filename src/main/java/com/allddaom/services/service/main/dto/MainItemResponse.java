package com.allddaom.services.service.main.dto;

import com.allddaom.models.items.domain.Item;
import com.allddaom.models.items.domain.ItemImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MainItemResponse {
    private Long id;
    private Long categoryId;
    private String categoryName;
    private String name;
    private String basicDescription;
    private String mainImageUrl;
    private List<String> imageUrl;
    private Long regularPrice; // 정상가
    private Long salePrice; // 판매가
    private Long percent; // 판매가
    private boolean isZzim;

    public static MainItemResponse of(Item item) {
        ItemImage mainImage = item.getMainImage();
        String mainImageUrl = "";
        if (!ObjectUtils.isEmpty(mainImage)) {
            mainImageUrl = mainImage.getUrl();
        }
        return MainItemResponse.builder()
                .id(item.getId())
                .categoryId(ObjectUtils.isEmpty(item.getCategory()) ? 0 : item.getCategory()
                        .getId())
                .categoryName(ObjectUtils.isEmpty(item.getCategory()) ? "" : item.getCategory()
                        .getName())
                .name(item.getName())
                .basicDescription(item.getBasicDescription())
                .regularPrice(item.getRegularPrice())
                .salePrice(item.getSalePrice())
                .percent(item.getPercent())
                .isZzim(false) // TODO 찜
                .mainImageUrl(mainImageUrl)
                .imageUrl(null)
                .build();
    }
}
