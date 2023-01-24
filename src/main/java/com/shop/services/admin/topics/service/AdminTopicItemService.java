package com.shop.services.admin.topics.service;

import com.shop.commons.entity.BasePage;
import com.shop.models.items.domain.Item;
import com.shop.models.items.service.ItemService;
import com.shop.models.topics.domain.Topic;
import com.shop.models.topics.domain.TopicItem;
import com.shop.models.topics.service.TopicItemService;
import com.shop.models.topics.service.TopicService;
import com.shop.services.admin.topics.dto.AdminTopicItemResponse;
import com.shop.services.admin.topics.dto.form.AdminTopicItemForm;
import com.shop.services.admin.topics.dto.form.AdminTopicItemItemForm;
import com.shop.services.admin.topics.dto.search.AdminTopicItemSearchDto;
import com.shop.services.admin.topics.dto.search.AdminTopicItemSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminTopicItemService {

    private final TopicService topicService;
    private final TopicItemService topicItemService;
    private final ItemService itemService;

    public AdminTopicItemSearchDto.Response search(String topicCode, AdminTopicItemSearchDto.Request dto, Pageable pageable) {
        Topic topic = topicService.findByCode(topicCode);
        Page<TopicItem> topicItemPage = topicItemService.searchForAdmin(topic, null, dto.getSearch(), pageable);
        Page<AdminTopicItemSearchResponse> topicItemSearchResponse = topicItemPage.map(AdminTopicItemSearchResponse::of);
        return AdminTopicItemSearchDto.Response.builder()
                .topicItemPage(new BasePage<>(topicItemSearchResponse))
                .build();
    }

    public AdminTopicItemForm getTopicItemForm(Long id) {
        TopicItem topicItem = topicItemService.findById(id);
        return AdminTopicItemForm.of(topicItem);
    }

    public AdminTopicItemResponse addOrUpdate(String topicCode, AdminTopicItemForm dto) {
        Long id = dto.getId();
        TopicItem topicItem;
        if (!ObjectUtils.isEmpty(id) && id != 0) {
            topicItem = topicItemService.findById(id);
        }

        List<Item> items = getItems(dto.getItems());
        Topic topic = topicService.findByCode(topicCode);

        topicItem = dto.entityBuilder()
                .items(items)
                .topic(topic)
                .build();
        topicItem = topicItemService.add(topicItem);
        return AdminTopicItemResponse.of(topicItem);
    }

    private List<Item> getItems(List<AdminTopicItemItemForm> itemsForm) {
        if (ObjectUtils.isEmpty(itemsForm)) {
            return null;
        }
        List<Long> itemIds = itemsForm.stream()
                .map(AdminTopicItemItemForm::getId)
                .collect(Collectors.toList());
        return itemService.findByIdIn(itemIds);
    }

}
