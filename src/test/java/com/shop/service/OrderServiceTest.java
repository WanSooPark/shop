//package com.shop.service;
//
//import com.shop.models.items.domain.ItemSellStatus;
//import com.shop.models.orders.domain.OrderStatus;
//import com.shop.service.orders.dto.OrderDto;
//import com.shop.models.items.domain.Item;
//import com.shop.models.members.domain.Member;
//import com.shop.models.orders.domain.Order;
//import com.shop.models.orders.domain.OrderItem;
//import com.shop.models.items.infra.repo.ItemRepository;
//import com.shop.models.members.infra.repo.MemberRepository;
//import com.shop.models.orders.infra.repo.OrderRepository;
//import com.shop.service.orders.service.OrderService;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//@Transactional
//@TestPropertySource(locations = "classpath:application-test.yml")
//class OrderServiceTest {
//
//    @Autowired
//    ItemRepository itemRepository;
//    @Autowired
//    MemberRepository memberRepository;
//    @Autowired
//    private OrderService orderService;
//    @Autowired
//    private OrderRepository orderRepository;
//
//    public Item saveItem() {
//        Item item = new Item();
//        item.setItemNm("테스트 상품");
//        item.setPrice(10000);
//        item.setItemDetail("테스트 상품 상세 설명");
//        item.setItemSellStatus(ItemSellStatus.SELL);
//        item.setStockNumber(100);
//        return itemRepository.save(item);
//    }
//
//    public Member saveMember() {
//        Member member = new Member();
//        member.setEmail("test@test.com");
//        return memberRepository.save(member);
//
//    }
//
//    @Test
//    @DisplayName("주문 테스트")
//    public void order() {
//        Item item = saveItem();
//        Member member = saveMember();
//
//        OrderDto orderDto = new OrderDto();
//        orderDto.setCount(10);
//        orderDto.setItemId(item.getId());
//
//        Long orderId = orderService.order(orderDto, member.getEmail());
//        Order order = orderRepository.findById(orderId)
//                .orElseThrow(EntityNotFoundException::new);
//
//        List<OrderItem> orderItems = order.getOrderItems();
//
//        int totalPrice = orderDto.getCount() * item.getPrice();
//
//        assertEquals(totalPrice, order.getTotalPrice());
//    }
//
//    @Test
//    @DisplayName("주문 취소 테스트")
//    public void cancelOrder() {
//        Item item = saveItem();
//        Member member = saveMember();
//
//        OrderDto orderDto = new OrderDto();
//        orderDto.setCount(10);
//        orderDto.setItemId(item.getId());
//        Long orderId = orderService.order(orderDto, member.getEmail());
//
//        Order order = orderRepository.findById(orderId)
//                .orElseThrow(EntityNotFoundException::new);
//        orderService.cancelOrder(orderId);
//
//        assertEquals(OrderStatus.CANCEL, order.getOrderStatus());
//        assertEquals(100, item.getStockNumber());
//    }
//
//}