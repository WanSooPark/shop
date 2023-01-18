package com.shop.services.service.items.service;

import com.shop.commons.entity.BasePage;
import com.shop.models.items.domain.Item;
import com.shop.models.items.service.ItemService;
import com.shop.services.service.items.dto.ServiceItemResponse;
import com.shop.services.service.items.dto.search.ServiceItemSearch;
import com.shop.services.service.items.dto.search.ServiceItemSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceItemService {

    private final ItemService itemService;

    public ServiceItemSearch.Response search(ServiceItemSearch.Request searchDto, Pageable pageable) {
        Page<Item> itemPage = itemService.search(searchDto.getCategoryId(), pageable);

        Page<ServiceItemSearchResponse> itemSearchResponsePage = itemPage.map(ServiceItemSearchResponse::of);
        return ServiceItemSearch.Response.builder()
                .itemPage(new BasePage<>(itemSearchResponsePage))
                .build();
    }

    public ServiceItemResponse getItem(Long id) {
        Item item = itemService.findById(id);
        return ServiceItemResponse.of(item);
    }

}
