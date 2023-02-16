package com.allddaom.services.service.carts.dto;

import com.allddaom.models.carts.domain.CartItem;
import com.allddaom.models.carts.domain.CartItemOption;
import com.allddaom.models.items.domain.Item;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;

@Data
@Builder
public class ServiceCartItemResponse {

    private Long id;
    private String name;
    private String brand;
    private String mainImageUrl;
    private Long price; // 판매가
    private Long count;
    @Builder.Default
    private Long earnedPoints = 0L;

    private ServiceCartItemOptionResponse option;

    public static ServiceCartItemResponse of(CartItem cartItem) {
        Item item = cartItem.getItem();
        CartItemOption cartItemOption = cartItem.getCartItemOption();

        long itemPrice = item.getSalePrice();
        long optionPrice = ObjectUtils.isEmpty(cartItemOption) || ObjectUtils.isEmpty(cartItemOption.getItemOption()) ? 0 : cartItemOption.getItemOption()
                .getPrice();
        long price = itemPrice + optionPrice;

        return ServiceCartItemResponse.builder()
                .id(cartItem.getId())
                .name(item.getName())
                .brand(item.getBrand())
                .price(price)
                .count(cartItem.getCount())
                .option(ServiceCartItemOptionResponse.of(cartItemOption))
                .mainImageUrl(ObjectUtils.isEmpty(item.getMainImage()) ? "/img/detail.png" : item.getMainImage()
                        .getUrl())
                .build();
    }

}
