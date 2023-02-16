package com.allddaom.services.service.carts.dto;

import com.allddaom.models.carts.domain.CartItemOption;
import com.allddaom.models.items.domain.ItemOption;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;

@Data
@Builder
public class ServiceCartItemOptionResponse {
    private Long id;
    private String name;
    private Long price;

    public static ServiceCartItemOptionResponse of(CartItemOption cartItemOption) {
        if (ObjectUtils.isEmpty(cartItemOption)) {
            return null;
        }
        ItemOption itemOption = cartItemOption.getItemOption();

        return ServiceCartItemOptionResponse.builder()
                .id(cartItemOption.getId())
                .name(itemOption.getName())
                .price(itemOption.getPrice())
                .build();
    }
}
