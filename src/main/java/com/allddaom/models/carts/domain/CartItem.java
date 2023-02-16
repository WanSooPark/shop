package com.allddaom.models.carts.domain;

import com.allddaom.commons.entity.BaseEntity;
import com.allddaom.models.items.domain.Item;
import com.allddaom.models.members.domain.Member;
import lombok.*;

import javax.persistence.*;

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

    private Long count;

    @OneToOne(cascade = CascadeType.ALL)
    private CartItemOption cartItemOption;

    public void changeCount(Long count) {
        this.count = count;
    }
}
