package com.shop.services.service.orders.dto.item;

import com.shop.models.orders.domain.OrderItem;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;

@Data
@Builder
public class ServiceOrderItemResponse {
    private Long id;
    private String name;
    private Long price;
    private Long count;
    private Long amount;
    private ServiceOrderItemOptionResponse option;

    public static ServiceOrderItemResponse of(OrderItem orderItem) {
        return ServiceOrderItemResponse.builder()
                .id(orderItem.getId())
                .name(orderItem.getName())
                .price(orderItem.getPrice())
                .count(orderItem.getCount())
                .amount(orderItem.getAmount())
                .option(ObjectUtils.isEmpty(orderItem.getOption()) ? null : ServiceOrderItemOptionResponse.of(orderItem.getOption()))
                .build();
    }

}
