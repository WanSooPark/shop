package com.shop.services.service.main.dto.cateogry;


import com.shop.models.categories.domain.Category;
import com.shop.services.service.main.dto.MainItemResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MainCategoryResponse {
    private Long id;
    private String name;
    private List<MainItemResponse> items;

    public static MainCategoryResponse of(Category category) {
        return MainCategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .items(ObjectUtils.isEmpty(category.getItems()) ? Collections.emptyList() : category.getItems()
                        .stream()
                        .map(MainItemResponse::of)
                        .collect(Collectors.toList()))
                .build();
    }

}
