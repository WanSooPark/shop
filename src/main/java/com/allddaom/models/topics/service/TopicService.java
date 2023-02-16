package com.allddaom.models.topics.service;

import com.allddaom.models.topics.domain.Topic;
import com.allddaom.models.topics.domain.TopicStatus;
import com.allddaom.models.topics.infra.repo.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository repository;

    public Page<Topic> searchForAdmin(String search, Pageable pageable) {
        return repository.searchForAdmin(search, pageable);
    }

    public Topic findByCode(String code) {
        return repository.findByCode(code);
    }

    public Topic save(Topic topic) {
        return repository.save(topic);
    }

    public boolean existsByCodeAndStatus(String topicCode, TopicStatus activate) {
        return repository.existsByCodeAndStatus(topicCode, TopicStatus.ACTIVATE);
    }

    public List<Topic> findByStatus(TopicStatus status) {
        return repository.findByStatus(status);
    }

    public Long count() {
        return repository.count();
    }

    public List<Topic> findByShowMain(boolean showMain) {
        return repository.findByShowMain(showMain);
    }
}
