package com.allddaom.services.service.faq.dto.search;

import com.allddaom.commons.entity.BasePage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ServiceFaqSearchDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private Long faqId;
        private String searchType;
        private String search;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private BasePage<ServiceFaqSearchResponse> faqPage;
    }

}
