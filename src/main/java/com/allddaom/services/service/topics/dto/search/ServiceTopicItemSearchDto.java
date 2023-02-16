package com.allddaom.services.service.topics.dto.search;

import com.allddaom.commons.entity.BasePage;
import com.allddaom.services.service.items.dto.search.ServiceItemSearchResponse;
import lombok.Builder;
import lombok.Data;

public class ServiceTopicItemSearchDto {

    @Data
    public static class Request {
        private Long topicItemId;
        private String search;
    }

    @Data
    @Builder
    public static class Response {
        private BasePage<ServiceItemSearchResponse> itemPage;
    }
}
