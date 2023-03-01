package com.allddaom.services.admin.terms.dto.search;

import com.allddaom.models.categories.domain.Category;
import com.allddaom.models.faq.domain.Faq;
import com.allddaom.models.terms.domain.Terms;
import com.allddaom.models.terms.domain.TermsCategory;
import com.allddaom.services.admin.faq.dto.search.AdminFaqSearchResponse;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@Data
@Builder
public class AdminTermsResponse {
    private Long id;
    private String content;
    private String category;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    public static AdminTermsResponse of(Terms terms) {
        return AdminTermsResponse.builder()
                .id(terms.getId())
                .content(terms.getContent())
                .category(terms.getCategory())
                .createdDateTime(terms.getCreatedDateTime())
                .updatedDateTime(terms.getUpdatedDateTime())
                .build();
    }
}
