package com.allddaom.models.itemreviews.domain;

import com.allddaom.commons.entity.BaseEntity;
import com.allddaom.models.members.domain.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ItemReview extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    private String content;

}
