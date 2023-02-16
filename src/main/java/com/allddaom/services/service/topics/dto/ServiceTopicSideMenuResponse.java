package com.allddaom.services.service.topics.dto;

import com.allddaom.models.topics.domain.Topic;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ServiceTopicSideMenuResponse {
    private String code;
    private String name;
    private List<ServiceTopicItemSideMenuResponse> topicItemMenus;

    public static ServiceTopicSideMenuResponse of(Topic topic) {
        return ServiceTopicSideMenuResponse.builder()
                .code(topic.getCode())
                .name(topic.getName())
                .build();
    }
}
