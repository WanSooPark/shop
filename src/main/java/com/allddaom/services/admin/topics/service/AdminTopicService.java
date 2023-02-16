package com.allddaom.services.admin.topics.service;

import com.allddaom.commons.entity.BasePage;
import com.allddaom.models.topics.domain.Topic;
import com.allddaom.models.topics.domain.TopicStatus;
import com.allddaom.models.topics.service.TopicService;
import com.allddaom.services.admin.topics.dto.AdminTopicResponse;
import com.allddaom.services.admin.topics.dto.form.AdminTopicForm;
import com.allddaom.services.admin.topics.dto.search.AdminTopicSearchDto;
import com.allddaom.services.admin.topics.dto.search.AdminTopicSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminTopicService {

    private final TopicService topicService;

    public AdminTopicSearchDto.Response search(AdminTopicSearchDto.Request dto, Pageable pageable) {
        Page<Topic> topicPage = topicService.searchForAdmin(dto.getSearch(), pageable);
        Page<AdminTopicSearchResponse> topicSearchResponsePage = topicPage.map(AdminTopicSearchResponse::of);
        return AdminTopicSearchDto.Response.builder()
                .topicPage(new BasePage<>(topicSearchResponsePage))
                .build();
    }

    public AdminTopicForm getTopicForm(String code) {
        Topic topic = topicService.findByCode(code);
        return AdminTopicForm.of(topic);
    }

    public AdminTopicResponse addOrUpdate(AdminTopicForm form) {
        String code = form.getCode();
        Topic topic = topicService.findByCode(code);
        if (ObjectUtils.isEmpty(topic)) {
            topic = form.toEntity();
        } else {
            boolean showMain = form.isShowMain();
            List<Topic> showMainTopics = topicService.findByShowMain(showMain);
            if (!ObjectUtils.isEmpty(showMainTopics)) {
                showMainTopics.forEach(showMainTopic -> {
                    showMainTopic.setShowMain(false);
                });
            }
            topic.update(form.getName(), form.getTopicStatus(), showMain);
        }
        topic = topicService.save(topic);
        return AdminTopicResponse.of(topic);
    }

    public boolean existsByTopicCode(String topicCode) {
        return topicService.existsByCodeAndStatus(topicCode, TopicStatus.ACTIVATE);
    }

    public AdminTopicResponse getTopic(String topicCode) {
        Topic topic = topicService.findByCode(topicCode);
        return AdminTopicResponse.of(topic);
    }
}
