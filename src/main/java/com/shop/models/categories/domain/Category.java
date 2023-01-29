package com.shop.models.categories.domain;

import com.shop.models.items.domain.Item;
import lombok.*;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.util.LinkedList;
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

    @Transient
    private List<Item> items;

    public List<Item> getItems() {
        if (ObjectUtils.isEmpty(this.items)) {
            this.items = new LinkedList<>();
        }
        return this.items;
    }
}
