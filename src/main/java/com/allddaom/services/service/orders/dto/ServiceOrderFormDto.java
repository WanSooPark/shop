package com.allddaom.services.service.orders.dto;

import com.allddaom.services.service.orders.form.OrderItemFormResponse;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class ServiceOrderFormDto {

    @Data
    public static class Request {
        private String type; // cart: 장바구니, item: 바로구매
        private Long[] cartItemIds;
        private Long[] cartItemCounts;
        private Long itemId;
        private Long itemCount;
        private Long itemOptionId;
    }

    @Data
    @Builder
    public static class Response {
        private List<OrderItemFormResponse> items;
        private Long totalPrice;
        private Long deliveryCost;
        private Long totalAmount;
        private Long reserves;

        public Long getTotalPrice() {
            return this.items.stream()
                    .mapToLong(cartItemResponse -> {
                        Long itemPrice = cartItemResponse.getPrice();
                        Long optionPrice = ObjectUtils.isEmpty(cartItemResponse.getOption()) ? 0 : cartItemResponse.getOption()
                                .getPrice();
                        Long count = cartItemResponse.getCount();
                        return (itemPrice + optionPrice) * count;
                    })
                    .sum();
        }

        public Long getDeliveryCost() {
            return 0L;
        }

        public Long getTotalAmount() {
            return this.getTotalPrice() + this.getDeliveryCost();
        }

        public Long getReserves() {
            return 0L;
        }
    }

}
