package com.allddaom.services.admin.faq.dto;

import com.allddaom.models.notices.domain.Notice;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminFaqResponse {
    private Long id;

    public static AdminFaqResponse of(Notice item) {
        return AdminFaqResponse.builder()
                .id(item.getId())
                .build();
    }
}
