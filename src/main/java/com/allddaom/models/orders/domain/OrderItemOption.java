package com.allddaom.models.orders.domain;

import com.allddaom.models.items.domain.ItemOption;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ItemOption itemOption;

    private String name;

    private Long price;

}
