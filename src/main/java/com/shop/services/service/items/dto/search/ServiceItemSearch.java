package com.shop.services.service.items.dto.search;

import com.shop.commons.entity.BasePage;
import lombok.Builder;
import lombok.Data;

public class ServiceItemSearch {

    @Data
    public static class Request {
        private Long categoryId;
        private String search;
    }

    @Data
    @Builder
    public static class Response {
        private BasePage<ServiceItemSearchResponse> itemPage;
    }
}