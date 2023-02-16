package com.allddaom.services.admin.topics.dto;

import com.allddaom.models.topics.domain.Topic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminTopicResponse {
    private String code;
    private String name;
    private String status;
    private LocalDateTime createdDateTime;

    public static AdminTopicResponse of(Topic topic) {
        return AdminTopicResponse.builder()
                .code(topic.getCode())
                .name(topic.getName())
                .status(topic.getStatus()
                        .name())
                .createdDateTime(topic.getCreatedDateTime())
                .build();
    }
}
