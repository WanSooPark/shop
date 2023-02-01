package com.shop.models.members.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum BirthCalendar {
    SOLAR_CALENDAR("양력"), LUNAR_CALENDAR("음력");

    private final String description;

    public static BirthCalendar getStringToEnum(String name) {
        if (!ObjectUtils.isEmpty(name)) {
            name = name.toUpperCase();
            for (BirthCalendar value : BirthCalendar.values()) {
                if (value.name()
                        .equals(name)) return value;
            }
        }
        return null;
    }

    public static BirthCalendar[] getStringsToEnums(String[] names) {
        if (!ObjectUtils.isEmpty(names) && names.length > 0) {
            BirthCalendar[] values = Arrays.stream(names)
                    .map(BirthCalendar::getStringToEnum)
                    .filter(Objects::nonNull)
                    .toArray(BirthCalendar[]::new);
            return values.length > 0 ? values : null;
        }
        return null;
    }
}
