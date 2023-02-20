package com.allddaom.services.service.faq.dto.search;

import com.allddaom.models.faq.domain.Faq;
import com.allddaom.models.notice.domain.Notice;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ServiceFaqSearchResponse {
    private Long id;
    private String title;
    private String content;
    private String category;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    public static ServiceFaqSearchResponse of(Faq faq) {
        return ServiceFaqSearchResponse.builder()
                .id(faq.getId())
                .title(faq.getTitle())
                .content(faq.getContent())
                .category(faq.getCategory())
                .createdDateTime(faq.getCreatedDateTime())
                .updatedDateTime(faq.getUpdatedDateTime())
                .build();
    }
}
