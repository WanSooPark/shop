package com.shop.commons.interceptor.dto;

import com.shop.models.topics.domain.Topic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterceptorTopicItemResponse {
    private String code;
    private String name;

    public static InterceptorTopicItemResponse of(Topic topic) {
        return InterceptorTopicItemResponse.builder()
                .code(topic.getCode())
                .name(topic.getName())
                .build();
    }
}
