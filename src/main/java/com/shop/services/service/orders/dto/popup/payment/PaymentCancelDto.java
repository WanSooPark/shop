package com.shop.services.service.orders.dto.popup.payment;

import lombok.Builder;
import lombok.Data;

public class PaymentCancelDto {

    @Data
    @Builder
    public static class Request {
        private String RETURNPARAMS;
        private Long orderId;
        private String paymentType;
    }

}
