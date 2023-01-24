package com.shop.services.admin.topics.dto.form;

import com.shop.models.items.domain.Item;
import com.shop.models.topics.domain.Topic;
import com.shop.models.topics.domain.TopicItem;
import com.shop.models.topics.domain.TopicItemStatus;
import com.shop.models.topics.domain.TopicStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminTopicItemForm {

    private Long id;
    private String title;
    private String subTitle;
    private String startDateTime;
    private String endDateTime;
    private Long ord; // 출력순서
    private String status;
    private List<AdminTopicItemItemForm> items;
    private Long[] itemIds;
    private String[] itemNames;
    private Long[] itemRegularPrices;
    private Long[] itemSalePrices;
    private Long[] itemStocks;

    public static AdminTopicItemForm of(TopicItem topicItem) {
        List<Long> ids = new LinkedList<>();
        List<String> names = new LinkedList<>();
        List<Long> regularPrices = new LinkedList<>();
        List<Long> salePrices = new LinkedList<>();
        List<Long> stocks = new LinkedList<>();

        List<Item> items = topicItem.getItems();
        items.forEach(item -> {
            ids.add(item.getId());
            names.add(item.getName());
            regularPrices.add(item.getRegularPrice());
            salePrices.add(item.getSalePrice());
            stocks.add(item.getStock());
        });

        return AdminTopicItemForm.builder()
                .id(topicItem.getId())
                .title(topicItem.getTitle())
                .subTitle(topicItem.getSubTitle())
                .startDateTime(topicItem.getStartDateTime()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .endDateTime(topicItem.getEndDateTime()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .ord(topicItem.getOrd())
                .status(topicItem.getStatus()
                        .name())
                .items(items
                        .stream()
                        .map(AdminTopicItemItemForm::of)
                        .collect(Collectors.toList()))

                .itemIds(ids.toArray(Long[]::new))
                .itemNames(names.toArray(String[]::new))
                .itemRegularPrices(regularPrices.toArray(Long[]::new))
                .itemSalePrices(salePrices.toArray(Long[]::new))
                .itemStocks(stocks.toArray(Long[]::new))
                .build();
    }

    public static AdminTopicItemForm empty() {
        return AdminTopicItemForm.builder()
                .id(0L)
                .status(TopicStatus.ACTIVATE.name())
                .items(new ArrayList<>())
                .startDateTime(LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .endDateTime(LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
    }

    public TopicItemStatus getTopicStatus() {
        return TopicItemStatus.getStringToEnum(this.status);
    }

    public List<AdminTopicItemItemForm> getItems() {
        this.items = new ArrayList<>();
        if (!ObjectUtils.isEmpty(this.itemIds)) {
            for (int i = 0; i < this.itemIds.length; i++) {
                AdminTopicItemItemForm itemItemForm = new AdminTopicItemItemForm();
                itemItemForm.setId(this.itemIds[i]);
                itemItemForm.setName(this.itemNames[i]);
                itemItemForm.setRegularPrice(this.itemRegularPrices[i]);
                itemItemForm.setSalePrice(this.itemSalePrices[i]);
                itemItemForm.setStock(this.itemStocks[i]);
                this.items.add(itemItemForm);
            }
        }

        return this.items;
    }

    /* 엔티티 빌더 */
    public TopicItemBuilder entityBuilder() {
        return new DefaultBuilder(this);
    }

    public interface TopicItemBuilder {
        TopicItemBuilder items(List<Item> items);

        TopicItemBuilder topic(Topic topic);

        TopicItem build();
    }

    private static class DefaultBuilder implements TopicItemBuilder {
        private final TopicItem topicItem;
        private final AdminTopicItemForm dto;

        public DefaultBuilder(AdminTopicItemForm dto) {
            this.topicItem = new TopicItem();
            this.dto = dto;
        }

        public DefaultBuilder(TopicItem topicItem, AdminTopicItemForm dto) {
            this.topicItem = topicItem;
            this.dto = dto;
        }

        @Override
        public TopicItemBuilder items(List<Item> items) {
            this.topicItem.setItems(items);
            return this;
        }

        @Override
        public TopicItemBuilder topic(Topic topic) {
            this.topicItem.setTopic(topic);
            return this;
        }

        @Override
        public TopicItem build() {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

            this.topicItem.setId(dto.getId());
            this.topicItem.setTitle(dto.getTitle());
            this.topicItem.setSubTitle(dto.getSubTitle());
            this.topicItem.setStartDateTime(LocalDateTime.parse(dto.getStartDateTime(), dateTimeFormatter));
            this.topicItem.setEndDateTime(LocalDateTime.parse(dto.getEndDateTime(), dateTimeFormatter));
            this.topicItem.setOrd(dto.getOrd()); // 출력순서
            this.topicItem.setStatus(TopicItemStatus.getStringToEnum(dto.getStatus()));
            return this.topicItem;
        }
    }

}
