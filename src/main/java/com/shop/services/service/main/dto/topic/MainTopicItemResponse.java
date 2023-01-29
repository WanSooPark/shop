package com.shop.services.service.main.dto.topic;

import com.shop.models.topics.domain.TopicItem;
import com.shop.services.service.main.dto.MainItemResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MainTopicItemResponse {
    private Long id;
    private String title;
    private List<MainItemResponse> items;

    public static MainTopicItemResponse of(TopicItem topicItem) {
        return MainTopicItemResponse.builder()
                .id(topicItem.getId())
                .title(topicItem.getTitle())
                .items(ObjectUtils.isEmpty(topicItem.getItems()) ? Collections.emptyList() : topicItem.getItems()
                        .stream()
                        .map(MainItemResponse::of)
                        .collect(Collectors.toList()))
                .build();
    }
}
