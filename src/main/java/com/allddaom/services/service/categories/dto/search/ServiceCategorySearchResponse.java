package com.allddaom.services.service.categories.dto.search;

import com.allddaom.models.categories.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
