package com.shop.commons.interceptor.service;

import com.shop.commons.interceptor.dto.InterceptorTopicResponse;
import com.shop.models.topics.domain.Topic;
import com.shop.models.topics.domain.TopicStatus;
import com.shop.models.topics.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminInterceptorTopicItemService {

    private final TopicService topicService;

    public List<InterceptorTopicResponse> getActivateTopicMenus() {
        List<Topic> topics = topicService.findByStatus(TopicStatus.ACTIVATE);
        return topics.stream()
                .map(InterceptorTopicResponse::of)
                .collect(Collectors.toList());
    }
}
