package com.shop.services.admin.categories.service;

import com.shop.commons.entity.BasePage;
import com.shop.models.categories.domain.Category;
import com.shop.models.categories.service.CategoryService;
import com.shop.services.admin.categories.dto.AdminCategoryAddDto;
import com.shop.services.admin.categories.dto.AdminCategoryResponse;
import com.shop.services.admin.categories.dto.AdminCategorySearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminCategoryService {

    private final CategoryService categoryService;

    public AdminCategorySearchDto.Response search(AdminCategorySearchDto.Request searchDto, Pageable pageable) {
        Page<Category> categoryPage = categoryService.searchForAdmin(searchDto.getSearch(), pageable);
        Page<AdminCategoryResponse> adminCategoryPage = categoryPage.map(AdminCategoryResponse::of);
        return AdminCategorySearchDto.Response.builder()
                .categoriesPage(new BasePage<>(adminCategoryPage))
                .build();
    }

    public AdminCategoryResponse getCategory(Long id) {
        Category category = categoryService.findById(id);
        return AdminCategoryResponse.of(category);
    }

    public AdminCategoryResponse add(AdminCategoryAddDto addDto) {
        Category topCategory = categoryService.findTopCategoryById(addDto.getTopCategoryId());

        Category category = addDto.entityBuilder()
                .topCategory(topCategory)
                .build();
        category = categoryService.add(category);
        return AdminCategoryResponse.of(category);
    }

    /**
     * 1분류, 2분류까지만 목록 조회
     * 트리구조를 -> 하나의 list 로
     */
    public List<AdminCategoryResponse> findTopCategories() {
        List<Category> sortCategories = new LinkedList<>();
        List<Category> categories = categoryService.findByDepthLessThanEqual(1);
        categories.forEach(category -> {
            sortCategories.add(category);
            List<Category> subCategories = categoryService.findByTopCategory(category);
            if (!ObjectUtils.isEmpty(subCategories)) {
                sortCategories.addAll(subCategories);
            }
        });

        return sortCategories.stream()
                .map(AdminCategoryResponse::of)
                .collect(Collectors.toList());
    }

    /**
     * 모든 카테고리 목록 조회
     * 트리구조를 -> 하나의 list 로
     */
    public List<AdminCategoryResponse> findAllCategories() {
        List<Category> sortCategories = new LinkedList<>();
        List<Category> categories = categoryService.findByDepthLessThanEqual(1);
        categories.forEach(category -> {
            sortCategories.add(category);
            List<Category> subCategories = categoryService.findByTopCategory(category);
            if (!ObjectUtils.isEmpty(subCategories)) {
                sortCategories.addAll(subCategories);

                subCategories.forEach(subCategory -> {
                    List<Category> lastCategories = categoryService.findByTopCategory(subCategory);
                    sortCategories.addAll(lastCategories);
                });
            }
        });

        return sortCategories.stream()
                .map(AdminCategoryResponse::of)
                .collect(Collectors.toList());
    }

    public List<AdminCategoryResponse> findLastDepthCategories() {
        List<Category> categories = categoryService.findByDepthLessThanEqual(3);

        return categories.stream()
                .map(AdminCategoryResponse::of)
                .collect(Collectors.toList());
    }

}
