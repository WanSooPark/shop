package com.allddaom.models.items.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum VatType {
    USE("과세"), UNUSED("비과세");

    private final String description;

    public static VatType getStringToEnum(String name) {
        if (!ObjectUtils.isEmpty(name)) {
            name = name.toUpperCase();
            for (VatType value : VatType.values()) {
                if (value.name()
                        .equals(name)) return value;
            }
        }
        return null;
    }

    public static VatType[] getStringsToEnums(String[] names) {
        if (!ObjectUtils.isEmpty(names) && names.length > 0) {
            VatType[] values = Arrays.stream(names)
                    .map(VatType::getStringToEnum)
                    .filter(Objects::nonNull)
                    .toArray(VatType[]::new);
            return values.length > 0 ? values : null;
        }
        return null;
    }
}
