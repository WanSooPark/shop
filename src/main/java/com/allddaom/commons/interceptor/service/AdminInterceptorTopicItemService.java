package com.allddaom.commons.interceptor.service;

import com.allddaom.commons.interceptor.dto.InterceptorTopicResponse;
import com.allddaom.models.topics.domain.Topic;
import com.allddaom.models.topics.domain.TopicStatus;
import com.allddaom.models.topics.service.TopicService;
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
