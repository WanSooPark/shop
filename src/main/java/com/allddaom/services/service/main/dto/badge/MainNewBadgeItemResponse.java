package com.allddaom.services.service.main.dto.badge;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MainNewBadgeItemResponse {
    private Long id;
    private String name;
    private boolean like;
}
