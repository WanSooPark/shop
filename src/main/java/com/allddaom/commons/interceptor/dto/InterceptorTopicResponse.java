package com.allddaom.commons.interceptor.dto;

import com.allddaom.models.topics.domain.Topic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterceptorTopicResponse {
    private String code;
    private String name;
    private List<InterceptorTopicItemResponse> topicItems;

    public static InterceptorTopicResponse of(Topic topic) {
        return InterceptorTopicResponse.builder()
                .code(topic.getCode())
                .name(topic.getName())
                .build();
    }
}
