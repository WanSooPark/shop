package com.shop.models.items.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum ItemOptionStatus {
    USE("사용함"), UNUSED("사용안함");

    private final String description;

    public static ItemOptionStatus getStringToEnum(String name) {
        if (!ObjectUtils.isEmpty(name)) {
            name = name.toUpperCase();
            for (ItemOptionStatus value : ItemOptionStatus.values()) {
                if (value.name()
                        .equals(name)) return value;
            }
        }
        return null;
    }

    public static ItemOptionStatus[] getStringsToEnums(String[] names) {
        if (!ObjectUtils.isEmpty(names) && names.length > 0) {
            ItemOptionStatus[] values = Arrays.stream(names)
                    .map(ItemOptionStatus::getStringToEnum)
                    .filter(Objects::nonNull)
                    .toArray(ItemOptionStatus[]::new);
            return values.length > 0 ? values : null;
        }
        return null;
    }
}
