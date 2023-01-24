package com.shop.models.topics.infra.repo;

import com.shop.models.topics.domain.Topic;
import com.shop.models.topics.domain.TopicItem;
import com.shop.models.topics.domain.TopicItemStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QDSLTopicItemRepository {
    Page<TopicItem> searchForAdmin(Topic topic, TopicItemStatus status, String search, Pageable pageable);
}
