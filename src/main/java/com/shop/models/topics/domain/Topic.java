package com.shop.models.topics.domain;

import com.shop.commons.entity.BaseEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

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

    private Long ord;

    public void update(String name, TopicStatus status) {
        this.name = name;
        this.status = status;
    }

    public boolean isActivate() {
        return this.status.equals(TopicStatus.ACTIVATE);
    }
}
