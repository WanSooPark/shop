package com.allddaom.services.admin.topics.dto.search;

import com.allddaom.commons.entity.BasePage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AdminTopicItemSearchDto {

    @Data
    public static class Request {
        private String search;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private BasePage<AdminTopicItemSearchResponse> topicItemPage;
    }
}
