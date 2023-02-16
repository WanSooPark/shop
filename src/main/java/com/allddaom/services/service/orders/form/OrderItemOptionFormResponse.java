package com.allddaom.services.service.orders.form;

import lombok.Builder;
import lombok.Data;

/**
 * 주문 상품 옵션
 * 주문서 작성 화면
 * form data
 */
@Data
@Builder
public class OrderItemOptionFormResponse {
    private Long id;
    private String name;
    private Long price;
}
