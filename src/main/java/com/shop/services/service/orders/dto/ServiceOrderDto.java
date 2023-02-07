package com.shop.services.service.orders.dto;

import lombok.Builder;
import lombok.Data;

public class ServiceOrderDto {

    @Data
    public static class Request {
        private Long[] itemIds;
        private Long[] itemCounts;
    }

    @Data
    @Builder
    public static class Response {
        private String message;
        private boolean success;
    }

}
