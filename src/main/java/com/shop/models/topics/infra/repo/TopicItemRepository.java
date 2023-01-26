package com.shop.models.topics.infra.repo;

import com.shop.models.topics.domain.Topic;
import com.shop.models.topics.domain.TopicItem;
import com.shop.models.topics.domain.TopicItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicItemRepository extends JpaRepository<TopicItem, Long>, QDSLTopicItemRepository {
    List<TopicItem> findByTopicAndStatusOrderByOrd(Topic topic, TopicItemStatus status);

    List<TopicItem> findByTopicCodeAndStatusOrderByOrd(String topicCode, TopicItemStatus status);
}
