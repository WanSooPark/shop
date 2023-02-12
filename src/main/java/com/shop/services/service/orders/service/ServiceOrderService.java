package com.shop.services.service.orders.service;

import com.shop.models.addresses.domain.Address;
import com.shop.models.carts.domain.CartItem;
import com.shop.models.carts.domain.CartItemOption;
import com.shop.models.carts.service.CartItemService;
import com.shop.models.items.domain.Item;
import com.shop.models.items.domain.ItemOption;
import com.shop.models.items.service.ItemOptionService;
import com.shop.models.items.service.ItemService;
import com.shop.models.members.domain.Member;
import com.shop.models.orders.domain.Order;
import com.shop.models.orders.domain.OrderItem;
import com.shop.models.orders.domain.OrderItemOption;
import com.shop.models.orders.service.OrderService;
import com.shop.services.service.orders.dto.ServiceOrderDto;
import com.shop.services.service.orders.dto.ServiceOrderFormDto;
import com.shop.services.service.orders.dto.ServiceOrderResponse;
import com.shop.services.service.orders.dto.address.ServiceOrderAddressDto;
import com.shop.services.service.orders.dto.item.ServiceOrderItemDto;
import com.shop.services.service.orders.dto.item.ServiceOrderItemOptionDto;
import com.shop.services.service.orders.form.OrderItemFormResponse;
import com.shop.services.service.orders.form.OrderItemOptionFormResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

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
        Long[] cartItemIds = dto.getCartItemIds();
        Long[] cartItemCounts = dto.getCartItemCounts();
        List<OrderItemFormResponse> items = new LinkedList<>();
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
                            .id(cartItemOption.getId())
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
                        .build();

                items.add(orderItem);
            }
        }

        return ServiceOrderFormDto.Response.builder()
                .items(items)
                .build();
    }
}
