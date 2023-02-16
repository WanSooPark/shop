package com.allddaom.services.admin.banners.dto.search;

import com.allddaom.commons.entity.BasePage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AdminBannerSearchDto {

    @Data
    public static class Request {
        private Long search;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private BasePage<AdminBannerSearchResponse> bannerPage;
    }
}
