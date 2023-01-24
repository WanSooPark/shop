package com.shop.models.topics.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum TopicStatus {
    ACTIVATE("활성화"), DEACTIVATE("비활성화");

    private final String description;

    public static TopicStatus getStringToEnum(String name) {
        if (!ObjectUtils.isEmpty(name)) {
            name = name.toUpperCase();
            for (TopicStatus value : TopicStatus.values()) {
                if (value.name()
                        .equals(name)) return value;
            }
        }
        return null;
    }

    public static TopicStatus[] getStringsToEnums(String[] names) {
        if (!ObjectUtils.isEmpty(names) && names.length > 0) {
            TopicStatus[] values = Arrays.stream(names)
                    .map(TopicStatus::getStringToEnum)
                    .filter(Objects::nonNull)
                    .toArray(TopicStatus[]::new);
            return values.length > 0 ? values : null;
        }
        return null;
    }
}
