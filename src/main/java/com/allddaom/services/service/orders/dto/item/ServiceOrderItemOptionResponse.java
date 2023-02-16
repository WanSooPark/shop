package com.allddaom.services.service.orders.dto.item;

import com.allddaom.models.orders.domain.OrderItemOption;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceOrderItemOptionResponse {
    private Long id;
    private String name;
    private Long price;

    public static ServiceOrderItemOptionResponse of(OrderItemOption option) {
        return ServiceOrderItemOptionResponse.builder()
                .id(option.getId())
                .name(option.getName())
                .price(option.getPrice())
                .build();
    }
}
