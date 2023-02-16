package com.allddaom.services.admin.categories.dto;

import com.allddaom.commons.entity.BasePage;
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
