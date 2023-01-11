package com.shop.services.admin.categories.dto;

import com.shop.commons.entity.BasePage;
import lombok.Builder;
import lombok.Data;

public class AdminCategorySearchDto {

    @Data
    public static class Request {
        private String search;
    }

    @Data
    @Builder
    public static class Response {
        private BasePage<AdminCategoryResponse> categoriesPage;
    }

}
