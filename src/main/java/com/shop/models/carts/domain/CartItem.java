package com.shop.models.carts.domain;

import com.shop.commons.entity.BaseEntity;
import com.shop.models.items.domain.Item;
import com.shop.models.members.domain.Member;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CartItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Item item;

    @OneToOne
    public CartItemOption cartItemOption;

    private Long count;

}
