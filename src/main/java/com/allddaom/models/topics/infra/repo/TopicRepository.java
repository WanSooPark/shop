package com.allddaom.models.topics.infra.repo;

import com.allddaom.models.topics.domain.Topic;
import com.allddaom.models.topics.domain.TopicStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, String>, QDSLTopicRepository {
    Topic findByCode(String code);

    boolean existsByCodeAndStatus(String code, TopicStatus activate);

    List<Topic> findByStatus(TopicStatus status);

    List<Topic> findByShowMain(boolean showMain);
}
