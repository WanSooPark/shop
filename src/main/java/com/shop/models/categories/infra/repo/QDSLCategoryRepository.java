package com.shop.models.categories.infra.repo;

import com.shop.models.categories.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QDSLCategoryRepository {
    Page<Category> searchForAdmin(String search, Pageable pageable);
}
