package com.shop.models.items.infra.repo;

import com.shop.models.items.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QDSLItemRepository {
    Page<Item> searchForAdmin(String searchType, String search, String categoryId, Pageable pageable);
}
