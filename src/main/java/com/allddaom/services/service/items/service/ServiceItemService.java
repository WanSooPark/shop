package com.allddaom.services.service.items.service;

import com.allddaom.commons.entity.BasePage;
import com.allddaom.models.items.domain.Item;
import com.allddaom.models.items.service.ItemService;
import com.allddaom.services.service.items.dto.ServiceItemResponse;
import com.allddaom.services.service.items.dto.search.ServiceItemSearchDto;
import com.allddaom.services.service.items.dto.search.ServiceItemSearchResponse;
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
