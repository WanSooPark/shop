package com.shop.models.categories.service;

import com.shop.commons.errors.exceptions.NoContentException;
import com.shop.models.categories.domain.Category;
import com.shop.models.categories.infra.repo.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public Category findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoContentException("유효하지 않은 분류 id 입니다."));
    }

    public Page<Category> searchForAdmin(String search, Pageable pageable) {
        return repository.searchForAdmin(search, pageable);
    }

    public Category add(Category category) {
        Category topCategory = category.getTopCategory();

        Category exists = repository.findByNameAndTopCategory(category.getName(), topCategory);
        if (!ObjectUtils.isEmpty(exists)) {
            // 같은 레벨 같은 이름의 카테고리가 있으면 추가 하지 않음
            exists.setStatus(category.getStatus());
            return exists;
        }

        if (!ObjectUtils.isEmpty(topCategory)) {
            Long topCategoryDepth = topCategory.getDepth();
            category.setDepth(topCategoryDepth + 1);
        } else {
            category.setDepth(1L);
        }

        return repository.save(category);
    }

    public Category findTopCategoryById(Long id) {
        return repository.findTopCategoryById(id);
    }

    public List<Category> findByTopCategory(Category topCategory) {
        return repository.findByTopCategory(topCategory);
    }

    public List<Category> findByTopCategoryId(Long topCategoryId) {
        return repository.findByTopCategoryId(topCategoryId);
    }

    public List<Category> findByDepthLessThanEqual(long depth) {
        return repository.findByDepthLessThanEqual(depth); // depth 보다 작거나 같은
    }

    public Category findByNameAndTopCategory(String name, Category topCategory) {
        return repository.findByNameAndTopCategory(name, topCategory);
    }

    public Category findByName(String name) {
        return repository.findByName(name);
    }
}
