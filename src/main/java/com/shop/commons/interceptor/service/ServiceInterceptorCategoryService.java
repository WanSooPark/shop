package com.shop.commons.interceptor.service;

import com.shop.models.categories.domain.Category;
import com.shop.models.categories.service.CategoryService;
import com.shop.services.service.categories.dto.search.ServiceCategorySearch;
import com.shop.services.service.categories.dto.search.ServiceCategorySearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceInterceptorCategoryService {

    private final CategoryService categoryService;

    public List<ServiceCategorySearchResponse> categoriesSearch(ServiceCategorySearch searchDto) {
        List<Category> categories = categoryService.findByDepthLessThanEqual(1);
        return categories.stream()
                .map(ServiceCategorySearchResponse::of) // 1depth
                .peek(category -> {
                    List<Category> subCategories = categoryService.findByTopCategoryId(category.getId());
                    List<ServiceCategorySearchResponse> subCategoriesResponse = subCategories.stream()
                            .map(ServiceCategorySearchResponse::of) // 2depth
                            .peek(subCategory -> {
                                List<Category> lastCategories = categoryService.findByTopCategoryId(subCategory.getId());
                                List<ServiceCategorySearchResponse> lastCategoriesResponse = lastCategories.stream()
                                        .map(ServiceCategorySearchResponse::of) // 3depth
                                        .collect(Collectors.toList());
                                subCategory.setSubCategories(lastCategoriesResponse);
                            })
                            .collect(Collectors.toList());
                    category.setSubCategories(subCategoriesResponse);
                })
                .collect(Collectors.toList());
    }

}
