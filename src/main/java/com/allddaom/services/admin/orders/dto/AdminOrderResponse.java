package com.allddaom.services.admin.orders.dto;

import com.allddaom.models.orders.domain.Order;
import com.allddaom.models.orders.domain.OrderItem;
import com.allddaom.services.admin.members.dto.AdminMemberResponse;
import com.allddaom.services.service.orders.dto.item.ServiceOrderItemResponse;
import com.allddaom.services.service.orders.dto.payment.ServicePaymentResponse;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class AdminOrderResponse {
    private Long id;
    private String orderNo;
    private String ordererName;
    private Long amount;
    private AdminMemberResponse member;
    private List<ServiceOrderItemResponse> items;
    private String status; // 주문상태
    private String statusName; // 주문상태
    private ServicePaymentResponse payment;
    private LocalDateTime orderDateTime; // 주문일시
    //    private LocalDateTime paidDateTime; // 결제일시
//    private LocalDateTime cancelDateTime; // 취소일시
//    private LocalDateTime exchangeDateTime; // 교환일시
//    private LocalDateTime returnDateTime; // 반품일시
//    private Address address;
    private String orderImageUrl;
    private String orderItemInfo; // 주문 상품 정보

    public static AdminOrderResponse of(Order order) {
        List<OrderItem> items = order.getItems();

        String orderImageUrl = "";
        String orderItemInfo = "";
        if (!ObjectUtils.isEmpty(items)) {
            OrderItem orderItem = items.get(0);
            orderImageUrl = orderItem.getItem()
                    .getMainImage()
                    .getUrl();
            orderItemInfo = orderItem.getName();
            if (items.size() > 1) {
                orderItemInfo += "외 " + items.size() + "개";
            }
        }

        return AdminOrderResponse.builder()
                .id(order.getId())
                .orderNo(order.getOrderNo())
                .ordererName(order.getOrdererName())
                .member(ObjectUtils.isEmpty(order.getMember()) ? null : AdminMemberResponse.of(order.getMember()))
                .amount(order.getAmount())
                .items(items
                        .stream()
                        .map(ServiceOrderItemResponse::of)
                        .collect(Collectors.toList()))
                .status(order.getStatus()
                        .name())
                .statusName(order.getStatus()
                        .getDescription())
                .payment(ObjectUtils.isEmpty(order.getPayment()) ? null : ServicePaymentResponse.of(order.getPayment()))
                .orderDateTime(order.getCreatedDateTime())
                .orderImageUrl(orderImageUrl)
                .orderItemInfo(orderItemInfo)
                .build();
    }
}
