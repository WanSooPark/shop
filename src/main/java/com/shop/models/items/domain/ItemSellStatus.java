package com.shop.models.items.domain;

import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Objects;

public enum ItemSellStatus {
    SELL, SOLD_OUT;

    public static ItemSellStatus getStringToEnum(String name) {
        if (!ObjectUtils.isEmpty(name)) {
            name = name.toUpperCase();
            for (ItemSellStatus value : ItemSellStatus.values()) {
                if (value.name()
                        .equals(name)) return value;
            }
        }
        return null;
    }

    public static ItemSellStatus[] getStringsToEnums(String[] names) {
        if (!ObjectUtils.isEmpty(names) && names.length > 0) {
            ItemSellStatus[] values = Arrays.stream(names)
                    .map(ItemSellStatus::getStringToEnum)
                    .filter(Objects::nonNull)
                    .toArray(ItemSellStatus[]::new);
            return values.length > 0 ? values : null;
        }
        return null;
    }
}