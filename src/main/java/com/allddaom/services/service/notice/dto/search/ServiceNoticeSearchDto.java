package com.allddaom.services.service.notice.dto.search;

import com.allddaom.commons.entity.BasePage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ServiceNoticeSearchDto {

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
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private BasePage<ServiceNoticeSearchResponse> noticePage;
    }

}
