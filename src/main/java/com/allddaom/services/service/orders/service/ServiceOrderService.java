package com.allddaom.services.service.orders.service;

import com.allddaom.commons.entity.BasePage;
import com.allddaom.commons.errors.exceptions.BadRequestException;
import com.allddaom.models.addresses.domain.Address;
import com.allddaom.models.carts.domain.CartItem;
import com.allddaom.models.carts.domain.CartItemOption;
import com.allddaom.models.carts.service.CartItemService;
import com.allddaom.models.items.domain.Item;
import com.allddaom.models.items.domain.ItemOption;
import com.allddaom.models.items.service.ItemOptionService;
import com.allddaom.models.items.service.ItemService;
import com.allddaom.models.members.domain.Member;
import com.allddaom.models.orders.domain.Order;
import com.allddaom.models.orders.domain.OrderItem;
import com.allddaom.models.orders.domain.OrderItemOption;
import com.allddaom.models.orders.domain.OrderStatus;
import com.allddaom.models.orders.service.OrderService;
import com.allddaom.services.service.orders.dto.ServiceOrderDto;
import com.allddaom.services.service.orders.dto.ServiceOrderFormDto;
import com.allddaom.services.service.orders.dto.ServiceOrderResponse;
import com.allddaom.services.service.orders.dto.address.ServiceOrderAddressDto;
import com.allddaom.services.service.orders.dto.item.ServiceOrderItemDto;
import com.allddaom.services.service.orders.dto.item.ServiceOrderItemOptionDto;
import com.allddaom.services.service.orders.dto.serarch.ServiceOrderSearchDto;
import com.allddaom.services.service.orders.form.OrderItemFormResponse;
import com.allddaom.services.service.orders.form.OrderItemOptionFormResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceOrderService {

    private final OrderService orderService;

    private final CartItemService cartItemService;

    private final ItemService itemService;
    private final ItemOptionService itemOptionService;

    private static Address mapAddress(ServiceOrderAddressDto.Request recipientAddressDto) {
        Address address = new Address();
        address.setPostcode(recipientAddressDto.getPostcode());
        address.setRoad(recipientAddressDto.getRoad());
        address.setDetail(recipientAddressDto.getDetail());
        return address;
    }

    public ServiceOrderDto.Response newOrder(ServiceOrderDto.Request dto, Member member) {
        List<OrderItem> orderItems = mapOrderItems(dto.getItems());

        ServiceOrderAddressDto.Request recipientAddressDto = dto.getRecipientAddress();
        Address address = mapAddress(recipientAddressDto);

        Order order = dto.toEntity(member, address);
        order.setItems(orderItems);

        order = orderService.add(order);

        return ServiceOrderDto.Response.builder()
                .order(ServiceOrderResponse.of(order))
                .build();
    }

    private List<OrderItem> mapOrderItems(List<ServiceOrderItemDto.Request> itemsDto) {
        return itemsDto.stream()
                .map(itemDto -> {
                    Item item = itemService.findById(itemDto.getId());
                    OrderItem orderItem = new OrderItem();
                    orderItem.setItem(item);
                    orderItem.setName(item.getName());
                    orderItem.setPrice(item.getSalePrice());
                    orderItem.setCount(itemDto.getCount());
                    orderItem.setCartItemId(itemDto.getCartItemId());

                    ServiceOrderItemOptionDto.Request option = itemDto.getOption();
                    OrderItemOption orderItemOption = mapOrderItemOption(option);
                    orderItem.setOption(orderItemOption);

                    return orderItem;
                })
                .collect(Collectors.toList());
    }

    private OrderItemOption mapOrderItemOption(ServiceOrderItemOptionDto.Request option) {
        OrderItemOption orderItemOption = null;
        if (!ObjectUtils.isEmpty(option)) {
            ItemOption itemOption = itemOptionService.findById(option.getId());
            orderItemOption = new OrderItemOption();
            orderItemOption.setItemOption(itemOption);
            orderItemOption.setName(itemOption.getName());
            orderItemOption.setPrice(itemOption.getPrice());
        }
        return orderItemOption;
    }

    public ServiceOrderFormDto.Response getOrderForm(ServiceOrderFormDto.Request dto) {
        List<OrderItemFormResponse> items = new LinkedList<>();

        String type = dto.getType();
        if (type.equals("cart")) { // 장바구니 구매
            Long[] cartItemIds = dto.getCartItemIds();
            Long[] cartItemCounts = dto.getCartItemCounts();

            if (!ObjectUtils.isEmpty(cartItemIds) && cartItemIds.length == cartItemCounts.length) {
                for (int i = 0; i < cartItemIds.length; i++) {
                    Long cartItemId = cartItemIds[i];
                    Long cartItemCount = cartItemCounts[i];

                    CartItem cartItem = cartItemService.findById(cartItemId);
                    Item item = cartItem.getItem();

                    CartItemOption cartItemOption = cartItem.getCartItemOption();
                    OrderItemOptionFormResponse option = null;
                    if (!ObjectUtils.isEmpty(cartItemOption)) {
                        ItemOption itemOption = cartItemOption.getItemOption();

                        option = OrderItemOptionFormResponse.builder()
                                .id(itemOption.getId())
                                .name(itemOption.getName())
                                .price(itemOption.getPrice())
                                .build();
                    }

                    Long price = item.getSalePrice();
                    price += ObjectUtils.isEmpty(option) ? 0L : option.getPrice();

                    OrderItemFormResponse orderItem = OrderItemFormResponse.builder()
                            .id(item.getId())
                            .name(item.getName())
                            .brand(item.getBrand())
                            .price(price)
                            .count(cartItemCount)
                            .option(option)
                            .cartItemId(cartItemId)
                            .mainImageUrl(ObjectUtils.isEmpty(item.getMainImage()) ? "/img/detail.png" : item.getMainImage()
                                    .getUrl())
                            .build();

                    items.add(orderItem);
                }
            }
        } else if (type.equals("item")) {
            Long itemId = dto.getItemId();
            Long itemCount = dto.getItemCount();
            Long itemOptionId = dto.getItemOptionId();

            Item item = itemService.findById(itemId);

            OrderItemOptionFormResponse option = null;
            if (!ObjectUtils.isEmpty(itemOptionId) && itemOptionId != 0) { // 옵션 정보 있을경우만
                ItemOption itemOption = itemOptionService.findById(itemOptionId);
                if (!ObjectUtils.isEmpty(itemOption)) {
                    option = OrderItemOptionFormResponse.builder()
                            .id(itemOption.getId())
                            .name(itemOption.getName())
                            .price(itemOption.getPrice())
                            .build();
                }
            }

            Long price = item.getSalePrice();
            price += ObjectUtils.isEmpty(option) ? 0L : option.getPrice();

            OrderItemFormResponse orderItem = OrderItemFormResponse.builder()
                    .id(item.getId())
                    .name(item.getName())
                    .brand(item.getBrand())
                    .price(price)
                    .count(itemCount)
                    .option(option)
                    .cartItemId(null)
                    .mainImageUrl(ObjectUtils.isEmpty(item.getMainImage()) ? "/img/detail.png" : item.getMainImage()
                            .getUrl())
                    .build();

            items.add(orderItem);
        } else {
            throw new BadRequestException("잘못된 type");
        }

        return ServiceOrderFormDto.Response.builder()
                .items(items)
                .build();
    }

    public ServiceOrderResponse getOrder(Long orderId) {
        Order order = orderService.findById(orderId);
        return ServiceOrderResponse.of(order);
    }

    public ServiceOrderSearchDto.Response search(ServiceOrderSearchDto.Request dto, Pageable pageable, Member member) {
        Page<Order> orderPage = orderService.search(dto.getStatusGroup(), dto.getStartDateTime(), dto.getEndDateTime(), member, pageable);
        Page<ServiceOrderResponse> orderResponsePage = orderPage.map(ServiceOrderResponse::of);
        return ServiceOrderSearchDto.Response.builder()
                .orderPage(new BasePage<>(orderResponsePage))
                .build();
    }

    public long countContentByValidGroup(ServiceOrderSearchDto.Request dto, Member member) {
        LocalDateTime startDateTime = dto.getStartDateTime();
        LocalDateTime endDateTime = dto.getEndDateTime();

        List<OrderStatus> statusList = new LinkedList<>();
        statusList.add(OrderStatus.BEFORE_DEPOSIT);
        statusList.add(OrderStatus.PREPARING_FOR_DELIVERY);
        statusList.add(OrderStatus.IN_DELIVERY);
        statusList.add(OrderStatus.DELIVERY_COMPLETE);
        return orderService.countByMemberAndCreatedDateTimeBetweenAndStatusIn(member, startDateTime, endDateTime, statusList);
    }

    public long countContentByInvalidGroup(ServiceOrderSearchDto.Request dto, Member member) {
        LocalDateTime startDateTime = dto.getStartDateTime();
        LocalDateTime endDateTime = dto.getEndDateTime();

        List<OrderStatus> statusList = new LinkedList<>();
        statusList.add(OrderStatus.CANCEL);
        statusList.add(OrderStatus.EXCHANGE);
        statusList.add(OrderStatus.RETURN);
        return orderService.countByMemberAndCreatedDateTimeBetweenAndStatusIn(member, startDateTime, endDateTime, statusList);
    }
}
