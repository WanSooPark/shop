package com.allddaom.models.members.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberStatus {
    NORMAL("정상"), ABNORMAL("비정상"), WITHDRAW("탈퇴");

    private final String description;
}
