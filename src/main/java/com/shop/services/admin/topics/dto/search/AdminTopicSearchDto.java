package com.shop.services.admin.topics.dto.search;

import com.shop.commons.entity.BasePage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AdminTopicSearchDto {

    @Data
    public static class Request {
        private String search;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private BasePage<AdminTopicSearchResponse> topicPage;
    }
}
