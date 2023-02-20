package com.allddaom.services.admin.notice.dto;

import com.allddaom.models.notices.domain.Notice;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminNoticeResponse {
    private Long id;

    public static AdminNoticeResponse of(Notice item) {
        return AdminNoticeResponse.builder()
                .id(item.getId())
                .build();
    }
}
