package com.allddaom.services.admin.terms.dto.search;

import com.allddaom.commons.entity.BasePage;
import lombok.Builder;
import lombok.Data;

public class AdminTermsSearchDto {

    @Data
    public static class Request {
        private String search;
    }

    @Data
    @Builder
    public static class Response {
        private BasePage<AdminTermsResponse> categoriesPage;
    }

}
