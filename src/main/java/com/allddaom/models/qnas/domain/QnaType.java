package com.allddaom.models.qnas.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QnaType {
    PRODUCT("상품"), DELIVERY("배송");

    private final String description;
}
