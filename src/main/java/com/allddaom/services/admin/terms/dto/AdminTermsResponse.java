package com.allddaom.services.admin.terms.dto;

import com.allddaom.models.terms.domain.Terms;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminTermsResponse {
    private Long id;

    public static AdminTermsResponse of(Terms item) {
        return AdminTermsResponse.builder()
                .id(item.getId())
                .build();
    }
}
