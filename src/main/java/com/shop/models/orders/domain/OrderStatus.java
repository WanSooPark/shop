package com.shop.models.orders.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    PENDING("발생 전"), BEFORE_DEPOSIT("입금 전"), PREPARING_FOR_DELIVERY("배송 준비중"), IN_DELIVERY("배송중"), DELIVERY_COMPLETE("배송완료"), CANCEL("취소"), EXCHANGE("교환"), RETURN("반품");

    private final String description;
}
