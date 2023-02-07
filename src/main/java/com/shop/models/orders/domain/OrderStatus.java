package com.shop.models.orders.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    ORDER("주문"), CANCEL("취소");

    private final String description;
}
