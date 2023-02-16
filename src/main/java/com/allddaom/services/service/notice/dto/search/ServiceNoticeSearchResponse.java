package com.allddaom.services.service.notice.dto.search;

import com.allddaom.models.notice.domain.Notice;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ServiceNoticeSearchResponse {
    private Long id;
    private String title;
    private String content;
    private Long viewCount;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    public static ServiceNoticeSearchResponse of(Notice notice) {
        return ServiceNoticeSearchResponse.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .viewCount(notice.getViewCount())
                .createdDateTime(notice.getCreatedDateTime())
                .updatedDateTime(notice.getUpdatedDateTime())
                .build();
    }
}
