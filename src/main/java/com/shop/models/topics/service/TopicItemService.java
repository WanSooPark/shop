package com.shop.models.topics.service;

import com.shop.commons.errors.exceptions.NoContentException;
import com.shop.models.topics.domain.Topic;
import com.shop.models.topics.domain.TopicItem;
import com.shop.models.topics.domain.TopicItemStatus;
import com.shop.models.topics.infra.repo.TopicItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TopicItemService {

    private final TopicItemRepository repository;

    public Page<TopicItem> searchForAdmin(Topic topic, TopicItemStatus status, String search, Pageable pageable) {
        return repository.searchForAdmin(topic, status, search, pageable);
    }

    public TopicItem findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoContentException("유효하지 않은 기획전 상품 id 입니다."));
    }

    public TopicItem add(TopicItem topicItem) {
        return repository.save(topicItem);
    }

    public TopicItem update(TopicItem topicItem) {
        return repository.save(topicItem);
    }

    public List<TopicItem> findByTopicAndStatus(Topic topic, TopicItemStatus status) {
        return repository.findByTopicAndStatusOrderByOrd(topic, status);
    }

    public List<TopicItem> findByTopicCodeAndStatus(String topicCode, TopicItemStatus status) {
        return repository.findByTopicCodeAndStatusOrderByOrd(topicCode, status);
    }

}
