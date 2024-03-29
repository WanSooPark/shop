package com.allddaom.services.admin.items.dto;

import com.allddaom.models.items.domain.Item;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminItemResponse {
    private Long id;

    public static AdminItemResponse of(Item item) {
        return AdminItemResponse.builder()
                .id(item.getId())
                .build();
    }
}
