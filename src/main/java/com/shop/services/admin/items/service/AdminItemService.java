package com.shop.services.admin.items.service;

import com.shop.services.service.items.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminItemService {

    private final ItemService itemService;

}
