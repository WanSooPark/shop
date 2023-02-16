package com.allddaom.models.items.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum Season {
    FW, SS;

    public static Season getStringToEnum(String name) {
        if (!ObjectUtils.isEmpty(name)) {
            name = name.toUpperCase();
            for (Season value : Season.values()) {
                if (value.name()
                        .equals(name)) return value;
            }
        }
        return null;
    }

    public static Season[] getStringsToEnums(String[] names) {
        if (!ObjectUtils.isEmpty(names) && names.length > 0) {
            Season[] values = Arrays.stream(names)
                    .map(Season::getStringToEnum)
                    .filter(Objects::nonNull)
                    .toArray(Season[]::new);
            return values.length > 0 ? values : null;
        }
        return null;
    }
}
