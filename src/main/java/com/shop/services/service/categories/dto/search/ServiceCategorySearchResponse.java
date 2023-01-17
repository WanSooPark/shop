package com.shop.services.service.categories.dto.search;

import com.shop.models.categories.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCategorySearchResponse {
    private Long id;
    private String name;
    private List<ServiceCategorySearchResponse> subCategories;

    public static ServiceCategorySearchResponse of(Category category) {
        return ServiceCategorySearchResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

}
