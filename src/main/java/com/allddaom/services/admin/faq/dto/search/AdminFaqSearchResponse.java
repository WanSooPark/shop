package com.allddaom.services.admin.faq.dto.search;

import com.allddaom.models.faqs.domain.Faq;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AdminFaqSearchResponse {
    private Long id;
    private String title;
    private String content;
    private String category;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    public static AdminFaqSearchResponse of(Faq faq) {
        return AdminFaqSearchResponse.builder()
                .id(faq.getId())
                .title(faq.getTitle())
                .content(faq.getContent())
                .category(faq.getCategory())
                .createdDateTime(faq.getCreatedDateTime())
                .updatedDateTime(faq.getUpdatedDateTime())
                .build();
    }
}
