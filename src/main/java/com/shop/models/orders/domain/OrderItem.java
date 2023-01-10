package com.shop.models.orders.domain;

import com.shop.commons.entity.BaseEntity;
import com.shop.models.items.domain.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Item item;

    @ManyToOne
    private Order order;

    private Long orderPrice; // 주문가격

    private Long count; // 수량

    public static OrderItem createOrderItem(Item item, long count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setCount(count);
        orderItem.setOrderPrice(item.getPrice());
        item.release(count);
        return orderItem;
    }

    public long getTotalPrice() {
        return orderPrice * count;
    }

    public void cancel() {
        this.getItem()
                .warehousing(count);
    }

}