package com.allddaom.models.qnas.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QnaAccessModifier {
    PUBLIC("공개"), PRIVATE("비밀");

    private final String description;
}
