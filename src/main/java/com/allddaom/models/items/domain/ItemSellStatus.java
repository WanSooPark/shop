package com.allddaom.models.items.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Objects;

@Deprecated
@Getter
@AllArgsConstructor
public enum ItemSellStatus {
    BEFORE_APPROVAL("승인 전"), SALE("판매 중"), PRODUCT_EXPOSURE("상품 노출"), SOLD_OUT("품절"), NO_SALE("판매 금지");

    private final String description;

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