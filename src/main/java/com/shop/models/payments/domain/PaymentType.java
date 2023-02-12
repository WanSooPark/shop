package com.shop.models.payments.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum PaymentType {
    CARD_PAYMENT("신용카드"), VIRTUAL_ACCOUNT("가상계좌"), WIRE_TRANSFER("계좌이체");

    private final String description;

    public static PaymentType getStringToEnum(String name) {
        if (!ObjectUtils.isEmpty(name)) {
            name = name.toUpperCase();
            for (PaymentType value : PaymentType.values()) {
                if (value.name()
                        .equals(name)) return value;
            }
        }
        return null;
    }

    public static PaymentType[] getStringsToEnums(String[] names) {
        if (!ObjectUtils.isEmpty(names) && names.length > 0) {
            PaymentType[] values = Arrays.stream(names)
                    .map(PaymentType::getStringToEnum)
                    .filter(Objects::nonNull)
                    .toArray(PaymentType[]::new);
            return values.length > 0 ? values : null;
        }
        return null;
    }
}
