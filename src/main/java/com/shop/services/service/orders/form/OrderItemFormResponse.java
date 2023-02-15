package com.shop.services.service.orders.form;

import lombok.Builder;
import lombok.Data;

/**
 * 주문 상품
 * 주문서 작성 화면
 * form data
 */
@Data
@Builder
public class OrderItemFormResponse {
    private Long id;
    private String name;
    private String brand;
    private Long price; // 판매가
    private Long count;
    @Builder.Default
    private Long earnedPoints = 0L;

    private OrderItemOptionFormResponse option;

    private Long cartItemId;
}
