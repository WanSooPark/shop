package com.shop.models.categories.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum CategoryStatus {
    EXPOSE("노출"), UNEXPOSED("비노출");

    private final String description;

    public static CategoryStatus getStringToEnum(String name) {
        if (!ObjectUtils.isEmpty(name)) {
            name = name.toUpperCase();
            for (CategoryStatus value : CategoryStatus.values()) {
                if (value.name()
                        .equals(name)) return value;
            }
        }
        return null;
    }

    public static CategoryStatus[] getStringsToEnums(String[] names) {
        if (!ObjectUtils.isEmpty(names) && names.length > 0) {
            CategoryStatus[] values = Arrays.stream(names)
                    .map(CategoryStatus::getStringToEnum)
                    .filter(Objects::nonNull)
                    .toArray(CategoryStatus[]::new);
            return values.length > 0 ? values : null;
        }
        return null;
    }
}
