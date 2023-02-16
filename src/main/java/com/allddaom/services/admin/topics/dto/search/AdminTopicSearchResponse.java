package com.allddaom.services.admin.topics.dto.search;

import com.allddaom.models.topics.domain.Topic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminTopicSearchResponse {

    private String code;
    private String name;
    private String status;

    public static AdminTopicSearchResponse of(Topic topic) {
        return AdminTopicSearchResponse.builder()
                .code(topic.getCode())
                .name(topic.getName())
                .status(topic.getStatus()
                        .name())
                .build();
    }

}
