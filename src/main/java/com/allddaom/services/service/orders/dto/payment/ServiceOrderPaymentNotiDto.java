package com.allddaom.services.service.orders.dto.payment;

import com.allddaom.models.payments.domain.PaymentType;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

public class ServiceOrderPaymentNotiDto {

    @Data
    public static class Request {
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

    @Data
    @Builder
    public static class Response {
        private String paymentType;
        private String message;
        private boolean success;

        private String returnCode; // 응답 코드
        private String returnMessage; // 응답 메시지
        private String startUrl; // 표준페이지 시작 다날 웹서버 URL
        private String startParams; // 표존 페이지에 전달할 params
        private String tid; // 다날 거래 키
        private String orderId; // 가맹점 주문번호
        private String amount; // 결제금액

        private String bankCode;
        private String bankName;
        private String expireDate;
        private String expireTime;
        private String virtualAccount;
        private String isCashReceipt;
        private String virtualAccountAmount;
    }

}
