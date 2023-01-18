package com.shop.services.service.main.service;

import com.shop.models.items.service.ItemService;
import com.shop.services.service.main.dto.badge.MainNewBadgeItemResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MainService {

    private final ItemService itemService;

    public List<MainNewBadgeItemResponse> findNewItems() {
        return null;
    }
}
