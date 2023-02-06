package com.shop.services.service.carts.dto.api;

import com.shop.services.service.carts.dto.ServiceCartItemResponse;
import lombok.Builder;
import lombok.Data;

public class ServiceCartItemDto {

    @Data
    public static class Request {
        private Long itemId;
        private Long optionId;
        private Long count;
    }

    @Data
    @Builder
    public static class Response {
        private String message;
        private boolean success;
        private ServiceCartItemResponse cartItem;
    }
}