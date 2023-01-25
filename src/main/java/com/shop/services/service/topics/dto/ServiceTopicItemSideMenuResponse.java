package com.shop.services.service.topics.dto;

import com.shop.models.topics.domain.TopicItem;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceTopicItemSideMenuResponse {
    private Long id;
    private String title;

    public static ServiceTopicItemSideMenuResponse of(TopicItem topicItem) {
        return ServiceTopicItemSideMenuResponse.builder()
                .id(topicItem.getId())
                .title(topicItem.getTitle())
                .build();
    }
}
