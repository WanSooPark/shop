package com.shop.services.admin.items.dto.search;

import com.shop.commons.entity.BasePage;
import lombok.Builder;
import lombok.Data;

public class AdminItemSearchDto {

    @Data
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
