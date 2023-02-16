package com.allddaom.models.orders.domain;

import com.allddaom.models.items.domain.Item;
import lombok.*;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Item item;

    @ManyToOne
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL)
    private OrderItemOption option;

    private String name;

    private Long price; // 주문가격

    private Long count; // 수량

    private Long cartItemId; // 장바구니 상품 아이디

    public Long getAmount() {
        long optionPrice = 0;
        if (!ObjectUtils.isEmpty(this.option)) {
            optionPrice = this.option.getPrice();
        }

        return (this.price + optionPrice) * this.count;
    }

}
