package com.allddaom.services.service.main.dto.topic;

import com.allddaom.models.topics.domain.Topic;
import com.allddaom.services.service.main.dto.MainItemResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MainTopicResponse {
    private String code;
    private String name;
    private List<MainTopicItemResponse> topicItems;
    private int size = 5;

    public static MainTopicResponse of(Topic topic) {
        return MainTopicResponse.builder()
                .code(topic.getCode())
                .name(topic.getName())
                .topicItems(ObjectUtils.isEmpty(topic.getTopicItems()) ? Collections.emptyList() : topic.getTopicItems()
                        .stream()
                        .map(MainTopicItemResponse::of)
                        .collect(Collectors.toList()))
                .build();
    }

    public int getSize() {
        AtomicInteger count = new AtomicInteger();
        if (!ObjectUtils.isEmpty(this.topicItems)) {
            this.topicItems.forEach(topicItem -> {
                if (!ObjectUtils.isEmpty(topicItem.getItems())) {
                    List<MainItemResponse> items = topicItem.getItems();
                    count.addAndGet(items.size());
                }
            });
        }
        return count.get();
    }
}
