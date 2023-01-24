package com.shop.services.admin.topics.dto.search;

import com.shop.models.categories.domain.Category;
import com.shop.models.items.domain.Item;
import com.shop.models.topics.domain.TopicItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminTopicItemSearchResponse {

    private Long id;
    private String title;
    private String subTitle;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String status;
    private Long ord;

    private List<AdminTopicItemItemSearchResponse> items;
    private String categoryName;
    private String itemName;
    private String itemDescription;
    private Long itemSalePrice;
    private Long itemRegularPrice;

    public static AdminTopicItemSearchResponse of(TopicItem topicItem) {
        String categoryName = "";
        String itemName = "";
        String itemDescription = "-";
        Long itemSalePrice = 0L;
        Long itemRegularPrice = 0L;
        if (!ObjectUtils.isEmpty(topicItem.getItems())) {
            if (!topicItem.getItems()
                    .isEmpty()) {
                List<Item> items = topicItem.getItems();
                Item firstItem = items.get(0);
                itemName = firstItem.getName();
                itemRegularPrice = firstItem.getRegularPrice();
                itemSalePrice = firstItem.getSalePrice();
                itemDescription = itemName + "외 " + items.size() + "개";

                Category category = firstItem
                        .getCategory();
                if (!ObjectUtils.isEmpty(category)) {
                    categoryName = category.getName();
                }
            }
        }

        return AdminTopicItemSearchResponse.builder()
                .id(topicItem.getId())
                .title(topicItem.getTitle())
                .subTitle(topicItem.getSubTitle())
                .startDateTime(topicItem.getStartDateTime())
                .endDateTime(topicItem.getEndDateTime())
                .status(topicItem.getStatus()
                        .name())
                .items(topicItem.getItems()
                        .stream()
                        .map(AdminTopicItemItemSearchResponse::of)
                        .collect(Collectors.toList()))
                .categoryName(categoryName)
                .itemName(itemName)
                .itemSalePrice(itemSalePrice)
                .itemRegularPrice(itemRegularPrice)
                .itemDescription(itemDescription)
                .build();
    }
}
