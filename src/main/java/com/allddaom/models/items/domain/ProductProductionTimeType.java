package com.allddaom.models.items.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum ProductProductionTimeType {
    NORMAL("정상"), CARRY_OVER("이월");

    private final String description;

    public static ProductProductionTimeType getStringToEnum(String name) {
        if (!ObjectUtils.isEmpty(name)) {
            name = name.toUpperCase();
            for (ProductProductionTimeType value : ProductProductionTimeType.values()) {
                if (value.name()
                        .equals(name)) return value;
            }
        }
        return null;
    }

    public static ProductProductionTimeType[] getStringsToEnums(String[] names) {
        if (!ObjectUtils.isEmpty(names) && names.length > 0) {
            ProductProductionTimeType[] values = Arrays.stream(names)
                    .map(ProductProductionTimeType::getStringToEnum)
                    .filter(Objects::nonNull)
                    .toArray(ProductProductionTimeType[]::new);
            return values.length > 0 ? values : null;
        }
        return null;
    }
}
