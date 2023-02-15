package com.shop.services.service.orders.dto.popup.payment;

import com.shop.models.payments.domain.PaymentType;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

public class PaymentStartDto {

    @Data
    @Builder
    public static class Request {
        private Long orderId;
        @NotBlank
        private String paymentType;
        //        private Long finalAmount;
//        private String itemName;
        private String useragent; // 사용자 환경 (WP: PC Web, WM: Mobile Web)
//        private String returnUrl;
//        private String cancelUrl;

        public PaymentType getPaymentType() {
            return PaymentType.getStringToEnum(this.paymentType);
        }
    }

}
