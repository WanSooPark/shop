package com.allddaom.services.service.orders.dto.payment;

import com.allddaom.models.payments.domain.PaymentType;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

public class ServiceOrderPaymentCompleteDto {

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
        private String message;
        private boolean success;
        private String paymentType;

        private String tid;
        private String username;
        private String userId;

        private String bankCode;
        private String bankName;

        /* 신용카드 필드 */
        private String trxAmount;
        private String tranDate;
        private String tranTime;
        private String cardCode;
        private String cardName;
        private String cardNo;
        private String quota;
        private String cardAuthNo;

        /* 가상계좌 필드 */
        private String amount;
        private String virtualAccount;
        private String accountHolder;
        private String userMail;
        private String itemName;
        private String byPassValue;
        private String expireDate;
        private String expireTime;
        private String isCashReceipt;

        /* 계좌이체 필드*/
        private String accountNo; // 출금계좌번호 뒷5자리(앞부분은 *로 표시)
        private String transTime; // 출금처리시간(yyyyMMDDmmHHss)
        private String userPhone;
        private String userEmail;
    }

}
