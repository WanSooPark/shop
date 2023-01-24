package com.shop.services.admin.topics.dto;

import com.shop.models.topics.domain.TopicItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminTopicItemResponse {

    private Long id;
    private String title;
    private String subTitle;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String status;

    public static AdminTopicItemResponse of(TopicItem topicItem) {
        return AdminTopicItemResponse.builder()
                .id(topicItem.getId())
                .title(topicItem.getTitle())
                .subTitle(topicItem.getSubTitle())
                .startDateTime(topicItem.getStartDateTime())
                .endDateTime(topicItem.getEndDateTime())
                .status(topicItem.getStatus()
                        .name())
                .build();
    }
}
