package com.allddaom.services.service.notice.dto;

import com.allddaom.models.notice.domain.Notice;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceNoticeResponse {
    private Long id;

    public static ServiceNoticeResponse of(Notice item) {
        return ServiceNoticeResponse.builder()
                .id(item.getId())
                .build();
    }
}
