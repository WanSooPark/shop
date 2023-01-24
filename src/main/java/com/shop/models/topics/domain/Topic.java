package com.shop.models.topics.domain;

import com.shop.commons.entity.BaseEntity;
import com.shop.models.items.domain.Item;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "code", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Topic extends BaseEntity {

    @Id
    private String code;

    private String name;

    @Enumerated(EnumType.STRING)
    private TopicStatus status;

    public void update(String name, TopicStatus status) {
        this.name = name;
        this.status = status;
    }
}
