package com.allddaom.commons.interceptor.dto;

import com.allddaom.models.topics.domain.TopicItem;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InterceptorTopicItemResponse {
    private Long id;
    private String title;

    public static InterceptorTopicItemResponse of(TopicItem topicItem) {
        return InterceptorTopicItemResponse.builder()
                .id(topicItem.getId())
                .title(topicItem.getTitle())
                .build();
    }
}
