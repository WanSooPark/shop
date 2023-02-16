package com.allddaom.services.service.topics.service;

import com.allddaom.commons.entity.BasePage;
import com.allddaom.commons.errors.exceptions.NoContentException;
import com.allddaom.commons.errors.exceptions.NotAcceptableStatusException;
import com.allddaom.models.items.domain.Item;
import com.allddaom.models.topics.domain.Topic;
import com.allddaom.models.topics.service.TopicItemService;
import com.allddaom.models.topics.service.TopicService;
import com.allddaom.services.service.categories.dto.search.ServiceCategoryItemSearchDto;
import com.allddaom.services.service.items.dto.search.ServiceItemSearchResponse;
import com.allddaom.services.service.topics.dto.search.ServiceTopicItemSearchDto;
import com.allddaom.services.service.topics.infra.repo.ServiceTopicItemItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceTopicItemService {

    private final TopicService topicService;
    private final TopicItemService topicItemService;
    private final ServiceTopicItemItemRepository serviceTopicItemItemRepository;

    public ServiceCategoryItemSearchDto.Response search(String topicCode, ServiceTopicItemSearchDto.Request dto, Pageable pageable) {
        Topic topic = topicService.findByCode(topicCode);
        if (ObjectUtils.isEmpty(topic)) {
            throw new NoContentException("유효하지 않은 기획전 입니다.");
        }
        if (!topic.isActivate()) {
            throw new NotAcceptableStatusException("비활성화 상태인 기획전 입니다.");
        }

        Page<Item> itemPage = null;
        if (ObjectUtils.isEmpty(dto.getTopicItemId())) {
            itemPage = serviceTopicItemItemRepository.searchItem(topic.getCode(), pageable);
        } else {
            itemPage = serviceTopicItemItemRepository.searchItem(topic.getCode(), dto.getTopicItemId(), pageable);
        }

        Page<ServiceItemSearchResponse> itemResponsePage = itemPage.map(ServiceItemSearchResponse::of);
        return ServiceCategoryItemSearchDto.Response.builder()
                .itemPage(new BasePage<>(itemResponsePage))
                .build();
    }

}
