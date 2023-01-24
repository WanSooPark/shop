package com.shop.models.topics.infra.repo;

import com.shop.models.topics.domain.TopicItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicItemRepository extends JpaRepository<TopicItem, Long>, QDSLTopicItemRepository {
}
