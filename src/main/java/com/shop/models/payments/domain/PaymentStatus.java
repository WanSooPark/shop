package com.shop.models.payments.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentStatus {
    READY("결제 준비"), COMPLETE("결제 완료"), SUCCESS("결제 성공"), FAILED("결제 실패"), CANCELED("취소");

    private final String description;
}
