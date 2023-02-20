package com.allddaom.models.qnas.domain;

import com.allddaom.commons.entity.BaseEntity;
import com.allddaom.models.members.domain.Member;
import lombok.*;

import javax.persistence.*;

/**
 * 상품 문의
 */
@Getter
@Setter
@Entity
@Builder
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Qna extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member writer;

    private String title;

    private String content;

    private String answer;

    @ManyToOne
    private Member answerer;

    @Enumerated(EnumType.STRING)
    private QnaStatus status;

    @Enumerated(EnumType.STRING)
    private QnaAccessModifier accessModifier; // 접근 제어 종류

    @Enumerated(EnumType.STRING)
    private QnaType type; // 문의 종류

    private String password;

}
