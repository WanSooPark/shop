package com.shop.services.service.categories.dto;

import com.shop.models.categories.domain.Category;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ServiceCategorySideMenuResponse {
    private Long id;
    private String name;
    private List<ServiceCategorySideMenuResponse> subCategories;

    public static ServiceCategorySideMenuResponse of(Category category) {
        return ServiceCategorySideMenuResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
