package com.shop.services.service.topics.dto;

import com.shop.models.topics.domain.Topic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceTopicResponse {
    private String code;
    private String name;

    public static ServiceTopicResponse of(Topic topic) {
        return ServiceTopicResponse.builder()
                .code(topic.getCode())
                .name(topic.getName())
                .build();
    }
}
