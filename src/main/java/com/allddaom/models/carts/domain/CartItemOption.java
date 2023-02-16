package com.allddaom.models.carts.domain;

import com.allddaom.models.items.domain.ItemOption;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CartItemOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ItemOption itemOption;

}
