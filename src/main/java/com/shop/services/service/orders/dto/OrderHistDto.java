package com.shop.services.service.orders.dto;//package com.shop.services.service.orders.dto;
//
//import com.shop.models.orders.domain.Order;
//import com.shop.models.orders.domain.OrderStatus;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//
//@Getter
//@Setter
//public class OrderHistDto {
//
//    private Long orderId; //주문아이디
//    private String orderDate; //주문날짜
//    private OrderStatus orderStatus; //주문 상태
//    private List<OrderItemDto> orderItemDtoList = new ArrayList<>();
//
//    public OrderHistDto(Order order) {
//        this.orderId = order.getId();
//        this.orderDate = order.getOrderDate()
//                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//        this.orderStatus = order.getOrderStatus();
//    }
//
//    //주문 상품리스트
//    public void addOrderItemDto(OrderItemDto orderItemDto) {
//        orderItemDtoList.add(orderItemDto);
//    }
//
//}