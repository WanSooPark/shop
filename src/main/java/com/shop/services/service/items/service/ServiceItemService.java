package com.shop.services.service.items.service;

import com.shop.models.items.domain.Item;
import com.shop.models.items.service.ItemService;
import com.shop.services.service.items.dto.ServiceItemResponse;
import com.shop.services.service.items.dto.search.ServiceItemSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceItemService {

    private final ItemService itemService;

    public ServiceItemSearch.Response search(ServiceItemSearch searchDto) {
        return ServiceItemSearch.Response.builder()
                .build();
    }

    public ServiceItemResponse getItem(Long id) {
        Item item = itemService.findById(id);
        return ServiceItemResponse.of(item);
    }

}
