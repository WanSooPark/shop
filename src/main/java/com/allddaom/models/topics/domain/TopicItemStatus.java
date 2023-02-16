package com.allddaom.models.topics.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum TopicItemStatus {
    ACTIVATE("활성화"), DEACTIVATE("비활성화");

    private final String description;

    public static TopicItemStatus getStringToEnum(String name) {
        if (!ObjectUtils.isEmpty(name)) {
            name = name.toUpperCase();
            for (TopicItemStatus value : TopicItemStatus.values()) {
                if (value.name()
                        .equals(name)) return value;
            }
        }
        return null;
    }

    public static TopicItemStatus[] getStringsToEnums(String[] names) {
        if (!ObjectUtils.isEmpty(names) && names.length > 0) {
            TopicItemStatus[] values = Arrays.stream(names)
                    .map(TopicItemStatus::getStringToEnum)
                    .filter(Objects::nonNull)
                    .toArray(TopicItemStatus[]::new);
            return values.length > 0 ? values : null;
        }
        return null;
    }
}
