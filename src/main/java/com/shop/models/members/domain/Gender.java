package com.shop.models.members.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE("남성"), WOMAN("여성");

    private final String description;

    public static Gender getStringToEnum(String name) {
        if (!ObjectUtils.isEmpty(name)) {
            name = name.toUpperCase();
            for (Gender value : Gender.values()) {
                if (value.name()
                        .equals(name)) return value;
            }
        }
        return null;
    }

    public static Gender[] getStringsToEnums(String[] names) {
        if (!ObjectUtils.isEmpty(names) && names.length > 0) {
            Gender[] values = Arrays.stream(names)
                    .map(Gender::getStringToEnum)
                    .filter(Objects::nonNull)
                    .toArray(Gender[]::new);
            return values.length > 0 ? values : null;
        }
        return null;
    }
}
