package com.shop.services.service.topics.service;

import com.shop.commons.errors.exceptions.NoContentException;
import com.shop.models.topics.domain.Topic;
import com.shop.models.topics.domain.TopicItem;
import com.shop.models.topics.domain.TopicItemStatus;
import com.shop.models.topics.service.TopicItemService;
import com.shop.models.topics.service.TopicService;
import com.shop.services.service.topics.dto.ServiceTopicItemSideMenuResponse;
import com.shop.services.service.topics.dto.ServiceTopicResponse;
import com.shop.services.service.topics.dto.ServiceTopicSideMenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceTopicService {

    private final TopicService topicService;
    private final TopicItemService topicItemService;

    public ServiceTopicResponse getTopic(String topicCode) {
        return ServiceTopicResponse.of(topicService.findByCode(topicCode));
    }

    public ServiceTopicSideMenuResponse getSideMenu(String topicCode) {
        Topic topic = topicService.findByCode(topicCode);
        if (ObjectUtils.isEmpty(topic)) {
            throw new NoContentException("유효하지 않은 Topic code 입니다.");
        }

        List<TopicItem> topicItems = topicItemService.findByTopicAndStatus(topic, TopicItemStatus.ACTIVATE);

        ServiceTopicSideMenuResponse menusResponse = ServiceTopicSideMenuResponse.of(topic);

        List<ServiceTopicItemSideMenuResponse> topicItemMenus = topicItems.stream()
                .map(ServiceTopicItemSideMenuResponse::of)
                .collect(Collectors.toList());
        menusResponse.setTopicItemMenus(topicItemMenus);
        return menusResponse;
    }
}
