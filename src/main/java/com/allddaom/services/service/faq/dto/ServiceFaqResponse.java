package com.allddaom.services.service.faq.dto;

import com.allddaom.models.faq.domain.Faq;
import com.allddaom.models.notice.domain.Notice;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceFaqResponse {
    private Long id;

    public static ServiceFaqResponse of(Faq item) {
        return ServiceFaqResponse.builder()
                .id(item.getId())
                .build();
    }
}
