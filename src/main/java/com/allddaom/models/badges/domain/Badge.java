package com.allddaom.models.badges.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
