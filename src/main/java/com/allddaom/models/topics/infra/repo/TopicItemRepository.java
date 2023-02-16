package com.allddaom.models.topics.infra.repo;

import com.allddaom.models.topics.domain.Topic;
import com.allddaom.models.topics.domain.TopicItem;
import com.allddaom.models.topics.domain.TopicItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TopicItemRepository extends JpaRepository<TopicItem, Long>, QDSLTopicItemRepository {
    List<TopicItem> findByTopicAndStatusOrderByOrd(Topic topic, TopicItemStatus status);

    List<TopicItem> findByTopicCodeAndStatusOrderByOrd(String topicCode, TopicItemStatus status);

    List<TopicItem> findByTopicAndStartDateTimeBeforeAndEndDateTimeAfterAndStatusOrderByOrd(Topic topic, LocalDateTime now1, LocalDateTime now2, TopicItemStatus status);
}
