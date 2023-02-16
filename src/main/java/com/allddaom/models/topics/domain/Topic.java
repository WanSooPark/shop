package com.allddaom.models.topics.domain;

import com.allddaom.commons.entity.BaseEntity;
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

    private Long ord;

    private boolean showMain;

    @Transient
    private List<TopicItem> topicItems;

    public void update(String name, TopicStatus status, boolean showMain) {
        this.name = name;
        this.status = status;
        this.showMain = showMain;
    }

    public boolean isActivate() {
        return this.status.equals(TopicStatus.ACTIVATE);
    }
}
