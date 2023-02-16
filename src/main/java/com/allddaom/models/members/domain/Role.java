package com.allddaom.models.members.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    USER("일반 사용자"), ADMIN("어드민 계정");

    private final String description;
}