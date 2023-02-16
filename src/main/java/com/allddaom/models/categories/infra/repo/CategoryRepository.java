package com.allddaom.models.categories.infra.repo;

import com.allddaom.models.categories.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long>, QDSLCategoryRepository {
    List<Category> findByDepthLessThanEqual(long i);

    Category findTopCategoryById(Long id);

    List<Category> findByTopCategory(Category topCategory);

    Category findByNameAndTopCategory(String name, Category topCategory);

    List<Category> findByTopCategoryId(Long topCategoryId);

    Category findByName(String name);
}