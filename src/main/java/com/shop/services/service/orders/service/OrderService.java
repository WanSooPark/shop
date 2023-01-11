package com.shop.services.service.orders.service;//package com.shop.services.service.orders.service;
//
//import com.shop.models.items.domain.Item;
//import com.shop.models.items.domain.ItemImage;
//import com.shop.models.items.infra.repo.ItemImageRepository;
//import com.shop.models.items.infra.repo.ItemRepository;
//import com.shop.models.members.domain.Member;
//import com.shop.models.members.infra.repo.MemberRepository;
//import com.shop.models.orders.domain.Order;
//import com.shop.models.orders.domain.OrderItem;
//import com.shop.models.orders.infra.repo.OrderRepository;
//import com.shop.services.service.orders.dto.OrderDto;
//import com.shop.services.service.orders.dto.OrderHistDto;
//import com.shop.services.service.orders.dto.OrderItemDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.thymeleaf.util.StringUtils;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@Transactional
//@RequiredArgsConstructor
//public class OrderService {
//
//    private final ItemRepository itemRepository;
//
//    private final MemberRepository memberRepository;
//
//    private final OrderRepository orderRepository;
//
//    private final ItemImageRepository itemImageRepository;
//
//    public Long order(OrderDto orderDto, String email) {
//
//        Item item = itemRepository.findById(orderDto.getItemId())
//                .orElseThrow(EntityNotFoundException::new);
//
//        Member member = memberRepository.findByEmail(email);
//
//        List<OrderItem> orderItemList = new ArrayList<>();
//        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
//        orderItemList.add(orderItem);
//        Order order = Order.createOrder(member, orderItemList);
//        orderRepository.save(order);
//
//        return order.getId();
//    }
//
//    @Transactional(readOnly = true)
//    public Page<OrderHistDto> getOrderList(String email, Pageable pageable) {
//
//        List<Order> orders = orderRepository.findOrders(email, pageable);
//        Long totalCount = orderRepository.countOrder(email);
//
//        List<OrderHistDto> orderHistDtos = new ArrayList<>();
//
//        for (Order order : orders) {
//            OrderHistDto orderHistDto = new OrderHistDto(order);
//            List<OrderItem> orderItems = order.getOrderItems();
//            for (OrderItem orderItem : orderItems) {
//                ItemImage itemImage = itemImageRepository.findByItemIdAndRepimgYn
//                        (orderItem.getItem()
//                                .getId(), "Y");
//                OrderItemDto orderItemDto =
//                        new OrderItemDto(orderItem, itemImage.getImgUrl());
//                orderHistDto.addOrderItemDto(orderItemDto);
//            }
//
//            orderHistDtos.add(orderHistDto);
//        }
//
//        return new PageImpl<OrderHistDto>(orderHistDtos, pageable, totalCount);
//    }
//
//    @Transactional(readOnly = true)
//    public boolean validateOrder(Long orderId, String email) {
//        Member curMember = memberRepository.findByEmail(email);
//        Order order = orderRepository.findById(orderId)
//                .orElseThrow(EntityNotFoundException::new);
//        Member savedMember = order.getMember();
//
//        return StringUtils.equals(curMember.getEmail(), savedMember.getEmail());
//    }
//
//    public void cancelOrder(Long orderId) {
//        Order order = orderRepository.findById(orderId)
//                .orElseThrow(EntityNotFoundException::new);
//        order.cancelOrder();
//    }
//
//    public Long orders(List<OrderDto> orderDtoList, String email) {
//
//        Member member = memberRepository.findByEmail(email);
//        List<OrderItem> orderItemList = new ArrayList<>();
//
//        for (OrderDto orderDto : orderDtoList) {
//            Item item = itemRepository.findById(orderDto.getItemId())
//                    .orElseThrow(EntityNotFoundException::new);
//
//            OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
//            orderItemList.add(orderItem);
//        }
//
//        Order order = Order.createOrder(member, orderItemList);
//        orderRepository.save(order);
//
//        return order.getId();
//    }
//
//}