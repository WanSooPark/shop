package com.allddaom.services.admin.topics.dto.search;

import com.allddaom.models.items.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminTopicItemItemSearchResponse {
    private Long id;
    private String name;
    private Long regularPrice; // 정상가
    private Long salePrice; // 판매가
    private String categoryName;

    public static AdminTopicItemItemSearchResponse of(Item item) {
        return AdminTopicItemItemSearchResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .regularPrice(item.getRegularPrice())
                .salePrice(item.getSalePrice())
                .categoryName(ObjectUtils.isEmpty(item.getCategory()) ? "" : item.getCategory()
                        .getName())
                .build();
    }
}
