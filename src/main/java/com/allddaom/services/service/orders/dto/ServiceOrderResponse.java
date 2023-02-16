package com.allddaom.services.service.orders.dto;

import com.allddaom.models.orders.domain.Order;
import com.allddaom.services.service.orders.dto.item.ServiceOrderItemResponse;
import com.allddaom.services.service.orders.dto.payment.ServicePaymentResponse;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ServiceOrderResponse {
    private Long id;
    private String orderNo;
    private Long amount;
    private List<ServiceOrderItemResponse> items;
    private String status; // 주문상태
    private ServicePaymentResponse payment;
//    private LocalDateTime orderDateTime; // 주문일시
//    private LocalDateTime paidDateTime; // 결제일시
//    private LocalDateTime cancelDateTime; // 취소일시
//    private LocalDateTime exchangeDateTime; // 교환일시
//    private LocalDateTime returnDateTime; // 반품일시
//    private Address address;

    public static ServiceOrderResponse of(Order order) {
        return ServiceOrderResponse.builder()
                .id(order.getId())
                .orderNo(order.getOrderNo())
                .amount(order.getAmount())
                .items(order.getItems()
                        .stream()
                        .map(ServiceOrderItemResponse::of)
                        .collect(Collectors.toList()))
                .status(order.getStatus()
                        .name())
                .payment(ObjectUtils.isEmpty(order.getPayment()) ? null : ServicePaymentResponse.of(order.getPayment()))
                .build();
    }
}
