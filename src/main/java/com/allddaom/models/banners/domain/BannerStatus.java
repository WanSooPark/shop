package com.allddaom.models.banners.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum BannerStatus {
    USE("사용함"), UNUSED("사용안함");

    private final String description;

    public static BannerStatus getStringToEnum(String name) {
        if (!ObjectUtils.isEmpty(name)) {
            name = name.toUpperCase();
            for (BannerStatus value : BannerStatus.values()) {
                if (value.name()
                        .equals(name)) return value;
            }
        }
        return null;
    }

    public static BannerStatus[] getStringsToEnums(String[] names) {
        if (!ObjectUtils.isEmpty(names) && names.length > 0) {
            BannerStatus[] values = Arrays.stream(names)
                    .map(BannerStatus::getStringToEnum)
                    .filter(Objects::nonNull)
                    .toArray(BannerStatus[]::new);
            return values.length > 0 ? values : null;
        }
        return null;
    }
}
