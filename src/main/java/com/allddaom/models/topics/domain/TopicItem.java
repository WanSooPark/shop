package com.allddaom.models.topics.domain;

import com.allddaom.models.items.domain.Item;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class TopicItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String subTitle;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private Long ord; // 출력순서

    @Enumerated(EnumType.STRING)
    private TopicItemStatus status;

    @ManyToOne
    private Topic topic;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "topic_item_x_item",
            joinColumns = @JoinColumn(name = "topic_item_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items;

}
