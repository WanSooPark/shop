package com.shop.models.categories.infra.repo;

import com.shop.models.categories.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long>, QDSLCategoryRepository {
    List<Category> findByDepthLessThanEqual(long i);

    Category findTopCategoryById(Long id);

    List<Category> findByTopCategory(Category topCategory);
}
