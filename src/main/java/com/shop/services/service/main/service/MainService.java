package com.shop.services.service.main.service;

import com.shop.models.items.service.ItemService;
import com.shop.models.members.domain.Member;
import com.shop.models.topics.domain.Topic;
import com.shop.models.topics.domain.TopicItem;
import com.shop.models.topics.domain.TopicItemStatus;
import com.shop.models.topics.service.TopicItemService;
import com.shop.models.topics.service.TopicService;
import com.shop.services.service.main.dto.topic.MainTopicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MainService {

    private final ItemService itemService;
    private final TopicService topicService;
    private final TopicItemService topicItemService;

    public MainTopicResponse findTodaySaleTopicItems(Member member) {
        Topic topic = topicService.findByCode("TODAY_SALE"); // 하드코딩

        List<TopicItem> topicItems = topicItemService.findEffectiveByTopicCodeAndStatusOrderByOrd(topic, TopicItemStatus.ACTIVATE);
        topic.setTopicItems(topicItems);

        // TODO 찜 추가
        return MainTopicResponse.of(topic);
    }

}
