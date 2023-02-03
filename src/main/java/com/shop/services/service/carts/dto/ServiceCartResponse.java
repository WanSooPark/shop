package com.shop.services.service.carts.dto;

import com.shop.models.carts.domain.CartItem;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ServiceCartResponse {
    private List<ServiceCartItemResponse> cartItems;
    private Long totalPrice;
    private Long deliveryCost;
    private Long totalAmount;

    public static ServiceCartResponse of(List<CartItem> cartItems) {
        List<ServiceCartItemResponse> cartItemResponses = cartItems.stream()
                .map(ServiceCartItemResponse::of)
                .collect(Collectors.toList());

        long totalPrice = cartItemResponses.stream()
                .mapToLong(cartItemResponse -> {
                    Long itemPrice = cartItemResponse.getPrice();
                    Long optionPrice = ObjectUtils.isEmpty(cartItemResponse.getOption()) ? 0 : cartItemResponse.getOption()
                            .getPrice();
                    return itemPrice + optionPrice;
                })
                .sum();
        long deliveryCost = 0;
        long totalAmount = totalPrice + deliveryCost;

        return ServiceCartResponse.builder()
                .cartItems(cartItemResponses)
                .totalPrice(totalPrice)
                .deliveryCost(deliveryCost)
                .totalAmount(totalAmount)
                .build();
    }
}
