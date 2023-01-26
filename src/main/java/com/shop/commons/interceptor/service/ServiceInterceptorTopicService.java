package com.shop.commons.interceptor.service;

import com.shop.commons.interceptor.dto.InterceptorTopicItemResponse;
import com.shop.commons.interceptor.dto.InterceptorTopicResponse;
import com.shop.models.topics.domain.Topic;
import com.shop.models.topics.domain.TopicItem;
import com.shop.models.topics.domain.TopicItemStatus;
import com.shop.models.topics.domain.TopicStatus;
import com.shop.models.topics.service.TopicItemService;
import com.shop.models.topics.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceInterceptorTopicService {

    private final TopicService topicService;
    private final TopicItemService topicItemService;

    public List<InterceptorTopicResponse> getActivateTopicMenus() {
        List<Topic> topics = topicService.findByStatus(TopicStatus.ACTIVATE);

        return topics.stream()
                .map(InterceptorTopicResponse::of)
                .peek(topicResponse -> {
                    List<TopicItem> topicItems = topicItemService.findByTopicCodeAndStatus(topicResponse.getCode(), TopicItemStatus.ACTIVATE);
                    topicResponse.setTopicItems(topicItems.stream()
                            .map(InterceptorTopicItemResponse::of)
                            .collect(Collectors.toList()));
                })
                .collect(Collectors.toList());
    }

}
