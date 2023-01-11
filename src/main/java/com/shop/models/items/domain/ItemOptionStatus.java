package com.shop.models.items.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemOptionStatus {
    USE("사용함"), UNUSED("사용안함");

    private final String description;
}
