package com.allddaom.services.service.orders.dto.popup.payment;

import com.allddaom.models.payments.domain.PaymentType;
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
        private boolean isMobile;
        // card payment 사용자 환경 (WP: PC Web, WM: Mobile Web)
        // virtual account (PC: PC Web, MW: Mobile Web)
        // wire transfer (PC: PC Web, MW: Mobile Web)
//        private String useragent;
//        private String returnUrl;
//        private String cancelUrl;

        public PaymentType getPaymentType() {
            return PaymentType.getStringToEnum(this.paymentType);
        }
    }

}
