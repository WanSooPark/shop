package com.shop.services.service.items.service;

import com.shop.commons.entity.BasePage;
import com.shop.models.items.domain.Item;
import com.shop.models.items.service.ItemService;
import com.shop.services.service.items.dto.ServiceItemResponse;
import com.shop.services.service.items.dto.search.ServiceItemSearchDto;
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

    public ServiceItemSearchDto.Response searchByCategory(ServiceItemSearchDto.Request searchDto, Pageable pageable) {
        Page<Item> itemPage = itemService.searchByCategory(searchDto.getCategoryId(), searchDto.getSearch(), pageable);

        Page<ServiceItemSearchResponse> itemSearchResponsePage = itemPage.map(ServiceItemSearchResponse::of);
        return ServiceItemSearchDto.Response.builder()
                .itemPage(new BasePage<>(itemSearchResponsePage))
                .build();
    }

    public ServiceItemResponse getItem(Long id) {
        Item item = itemService.findById(id);
        return ServiceItemResponse.of(item);
    }

}
