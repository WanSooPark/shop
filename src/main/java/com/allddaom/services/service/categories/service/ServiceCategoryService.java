package com.allddaom.services.service.categories.service;

import com.allddaom.models.categories.domain.Category;
import com.allddaom.models.categories.service.CategoryService;
import com.allddaom.services.service.categories.dto.ServiceCategoryResponse;
import com.allddaom.services.service.categories.dto.ServiceCategorySideMenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceCategoryService {

    private final CategoryService categoryService;

    public ServiceCategorySideMenuResponse getSideMenu(Long categoryId) {
        Category category = categoryService.findById(categoryId);
        while (!ObjectUtils.isEmpty(category.getTopCategory())) {
            category = category.getTopCategory();
        }

        ServiceCategorySideMenuResponse response = ServiceCategorySideMenuResponse.of(category);
        List<Category> subCategories = categoryService.findByTopCategory(category);
        response.setSubCategories(subCategories.stream()
                .map(ServiceCategorySideMenuResponse::of)
                .peek(subCategory -> {
                    List<Category> lastCategories = categoryService.findByTopCategoryId(subCategory.getId());
                    subCategory.setSubCategories(lastCategories.stream()
                            .map(ServiceCategorySideMenuResponse::of)
                            .collect(Collectors.toList()));
                })
                .collect(Collectors.toList()));
        return response;
    }

    public ServiceCategoryResponse getCategory(Long categoryId) {
        return ServiceCategoryResponse.of(categoryService.findById(categoryId));
    }
}
