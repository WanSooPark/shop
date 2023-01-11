package com.shop.models.categories.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoryStatus {
    EXPOSE("노출"), UNEXPOSED("비노출");

    private final String description;
}
