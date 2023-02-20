package com.allddaom.services.admin.faq.dto.search;

import com.allddaom.commons.entity.BasePage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AdminFaqSearchDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private String category;
        private String searchType;
        private String search;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private BasePage<AdminFaqSearchResponse> faqPage;
    }

}
