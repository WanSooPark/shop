package com.shop.services.admin.categories.dto;

import com.shop.models.categories.domain.Category;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;

@Data
@Builder
public class AdminCategoryResponse {
    private Long id;
    private String name;
    private Long depth;
    private AdminCategoryResponse topCategory;
    private Long topCategoryId;
    private Long topCategoryDepth;

    public static AdminCategoryResponse of(Category category) {
        Category topCategory = category.getTopCategory();

        return AdminCategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .depth(category.getDepth())
                .topCategory(ObjectUtils.isEmpty(topCategory) ? null : AdminCategoryResponse.of(topCategory))
                .topCategoryId(ObjectUtils.isEmpty(topCategory) ? null : topCategory.getId())
                .topCategoryDepth(ObjectUtils.isEmpty(topCategory) ? null : topCategory.getDepth())
                .build();
    }

    public static AdminCategoryResponse empty() {
        return AdminCategoryResponse.builder()
                .build();
    }
}
