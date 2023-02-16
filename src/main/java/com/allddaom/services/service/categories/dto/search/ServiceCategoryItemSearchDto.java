package com.allddaom.services.service.categories.dto.search;

import com.allddaom.commons.entity.BasePage;
import com.allddaom.services.service.items.dto.search.ServiceItemSearchResponse;
import lombok.Builder;
import lombok.Data;

public class ServiceCategoryItemSearchDto {

    @Data
    public static class Request {
        private String search;
    }

    @Data
    @Builder
    public static class Response {
        private BasePage<ServiceItemSearchResponse> itemPage;
    }

}
