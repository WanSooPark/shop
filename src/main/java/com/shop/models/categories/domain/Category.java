package com.shop.models.categories.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long depth;

    @ManyToOne
    private Category topCategory;

    @Enumerated(EnumType.STRING)
    private CategoryStatus status;

}
