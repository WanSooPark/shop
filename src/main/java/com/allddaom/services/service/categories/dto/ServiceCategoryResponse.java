package com.allddaom.services.service.categories.dto;

import com.allddaom.models.categories.domain.Category;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceCategoryResponse {
    private Long id;
    private String name;

    public static ServiceCategoryResponse of(Category category) {
        return ServiceCategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
