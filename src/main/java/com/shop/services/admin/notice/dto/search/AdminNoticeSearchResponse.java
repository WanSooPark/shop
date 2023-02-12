package com.shop.services.admin.notice.dto.search;

import com.shop.models.items.domain.Item;
import com.shop.models.notice.domain.Notice;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@Data
@Builder
public class AdminNoticeSearchResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    public static AdminNoticeSearchResponse of(Notice notice) {
        return AdminNoticeSearchResponse.builder()
                .id(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .createdDateTime(notice.getCreatedDateTime())
                .updatedDateTime(notice.getUpdatedDateTime())
                .build();
    }
}
