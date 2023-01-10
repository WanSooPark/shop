package com.shop.models.items.infra.repo;

import com.shop.models.items.domain.Item;
import com.shop.services.service.items.dto.ItemSearchDto;
import com.shop.services.service.main.dto.MainItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

}