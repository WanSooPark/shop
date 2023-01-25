package com.shop.services.service.topics.service;

import com.shop.commons.entity.BasePage;
import com.shop.commons.errors.exceptions.NoContentException;
import com.shop.commons.errors.exceptions.NotAcceptableStatusException;
import com.shop.models.items.domain.Item;
import com.shop.models.topics.domain.Topic;
import com.shop.models.topics.service.TopicItemService;
import com.shop.models.topics.service.TopicService;
import com.shop.services.service.categories.dto.search.ServiceCategoryItemSearchDto;
import com.shop.services.service.items.dto.search.ServiceItemSearchResponse;
import com.shop.services.service.topics.dto.search.ServiceTopicItemSearchDto;
import com.shop.services.service.topics.infra.repo.ServiceTopicItemItemRepository;
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
