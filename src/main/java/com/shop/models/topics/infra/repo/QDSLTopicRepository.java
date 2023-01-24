package com.shop.models.topics.infra.repo;

import com.shop.models.topics.domain.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QDSLTopicRepository {
    Page<Topic> searchForAdmin(String search, Pageable pageable);
}
