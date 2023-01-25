package com.shop.services.service.topics.dto.search;

import com.shop.commons.entity.BasePage;
import com.shop.services.service.items.dto.search.ServiceItemSearchResponse;
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
