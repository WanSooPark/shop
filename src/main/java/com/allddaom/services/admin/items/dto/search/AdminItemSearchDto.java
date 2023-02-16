package com.allddaom.services.admin.items.dto.search;

import com.allddaom.commons.entity.BasePage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AdminItemSearchDto {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {
        private Long categoryId;
        private String searchType;
        private String search;
    }

    @Data
    @Builder
    public static class Response {
        private BasePage<AdminItemSearchResponse> itemPage;
    }

}
