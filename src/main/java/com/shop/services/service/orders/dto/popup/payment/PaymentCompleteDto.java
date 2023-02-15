package com.shop.services.service.orders.dto.popup.payment;

import lombok.Builder;
import lombok.Data;

public class PaymentCompleteDto {

    @Data
    @Builder
    public static class Request {
        private String RETURNPARAMS;
    }

}
