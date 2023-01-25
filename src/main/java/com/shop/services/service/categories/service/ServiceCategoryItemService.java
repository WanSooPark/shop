package com.shop.services.service.categories.service;

import com.shop.commons.entity.BasePage;
import com.shop.models.items.domain.Item;
import com.shop.models.items.service.ItemService;
import com.shop.services.service.categories.dto.search.ServiceCategoryItemSearchDto;
import com.shop.services.service.items.dto.search.ServiceItemSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceCategoryItemService {

    private final ItemService itemService;

    public ServiceCategoryItemSearchDto.Response search(Long categoryId, ServiceCategoryItemSearchDto.Request searchDto, Pageable pageable) {
        Page<Item> itemPage = itemService.searchByCategory(categoryId, searchDto.getSearch(), pageable);

        Page<ServiceItemSearchResponse> itemSearchResponsePage = itemPage.map(ServiceItemSearchResponse::of);
        return ServiceCategoryItemSearchDto.Response.builder()
                .itemPage(new BasePage<>(itemSearchResponsePage))
                .build();
    }

}
