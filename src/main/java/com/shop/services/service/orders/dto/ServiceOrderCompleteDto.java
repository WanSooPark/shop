package com.shop.services.service.orders.dto;

import lombok.Data;

public class ServiceOrderCompleteDto {

    @Data
    public static class Request {
        private Long orderId;
    }

}
