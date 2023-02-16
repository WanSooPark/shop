package com.allddaom.services.admin.topics.dto.form;

import com.allddaom.models.items.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminTopicItemItemForm {
    private Long id;
    private String name;
    private Long regularPrice; // 정상가
    private Long salePrice; // 판매가
    private Long stock; // 판매가

    public static AdminTopicItemItemForm of(Item item) {
        return AdminTopicItemItemForm.builder()
                .id(item.getId())
                .name(item.getName())
                .regularPrice(item.getRegularPrice())
                .salePrice(item.getSalePrice())
                .stock(item.getStock())
                .build();
    }

}
