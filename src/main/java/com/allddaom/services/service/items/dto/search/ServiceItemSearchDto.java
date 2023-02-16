package com.allddaom.services.service.items.dto.search;

import com.allddaom.commons.entity.BasePage;
import lombok.Builder;
import lombok.Data;

public class ServiceItemSearchDto {

    @Data
    public static class Request {
        private Long categoryId;
        private Long topicCode;
        private String search;
    }

    @Data
    @Builder
    public static class Response {
        private BasePage<ServiceItemSearchResponse> itemPage;
    }

}
