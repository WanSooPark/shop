package com.shop.models.members.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberStatus {
    NORMAL("정상"), ABNORMAL("비정상");

    private final String description;
}
