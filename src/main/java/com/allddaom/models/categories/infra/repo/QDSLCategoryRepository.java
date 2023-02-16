package com.allddaom.models.categories.infra.repo;

import com.allddaom.models.categories.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QDSLCategoryRepository {
    Page<Category> searchForAdmin(String search, Pageable pageable);
}
