package com.shop.services.service.orders.dto.payment;

import lombok.Builder;
import lombok.Data;

public class ServiceOrderPaymentCancelDto {

    @Data
    @Builder
    public static class Response {
        private String paymentType;
        private String message;
        private boolean success;
    }

}
