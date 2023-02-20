package com.allddaom.models.qnas.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QnaStatus {
    BEFORE_ANSWER("답변 대기"), ANSWER_COMPLETE("답변 완료");

    private final String description;
}
