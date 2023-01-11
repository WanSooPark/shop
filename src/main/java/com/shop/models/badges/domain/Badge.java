package com.shop.models.badges.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(of = "code", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Badge {

    @Id
    private String code;
    private String name;

}
