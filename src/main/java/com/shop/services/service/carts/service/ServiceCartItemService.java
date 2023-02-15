package com.shop.services.service.carts.service;

import com.shop.commons.errors.exceptions.AccessDeniedException;
import com.shop.commons.errors.exceptions.NoContentException;
import com.shop.models.carts.domain.CartItem;
import com.shop.models.carts.domain.CartItemOption;
import com.shop.models.carts.service.CartItemService;
import com.shop.models.items.domain.Item;
import com.shop.models.items.domain.ItemOption;
import com.shop.models.items.service.ItemOptionService;
import com.shop.models.items.service.ItemService;
import com.shop.models.members.domain.Member;
import com.shop.services.service.carts.dto.ServiceCartItemResponse;
import com.shop.services.service.carts.dto.api.ServiceCartItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceCartItemService {

    private final CartItemService cartItemService;
    private final ItemService itemService;
    private final ItemOptionService itemOptionService;

    public ServiceCartItemDto.Response add(ServiceCartItemDto.Request dto, Member member) {
        Long itemId = dto.getItemId();
        Long itemOptionId = dto.getOptionId();
        Long count = dto.getCount();

        Item item = null;
        ItemOption itemOption = null;
        try {
            item = itemService.findById(itemId);
            if (!ObjectUtils.isEmpty(itemOptionId)) {
                itemOption = itemOptionService.findById(itemOptionId);
            }
        } catch (NoContentException noContentException) {
            return ServiceCartItemDto.Response.builder()
                    .message(noContentException.getMessage())
                    .success(false)
                    .build();
        }

        CartItem cartItem = new CartItem();
        cartItem.setItem(item);
        cartItem.setCount(count);
        cartItem.setMember(member);

        CartItemOption cartItemOption = null;
        if (!ObjectUtils.isEmpty(itemOption)) {
            cartItemOption = new CartItemOption();
            cartItemOption.setItemOption(itemOption);
            cartItem.setCartItemOption(cartItemOption);
        }

        cartItem = cartItemService.add(cartItem);

        return ServiceCartItemDto.Response.builder()
                .message("장바구니 등록 성공")
                .success(true)
                .cartItem(ServiceCartItemResponse.of(cartItem))
                .build();
    }

    public ServiceCartItemResponse changeCartItemCount(Long cartItemId, ServiceCartItemDto.ChangeCountRequest dto, Member member) {
        CartItem cartItem = cartItemService.findById(cartItemId);

        if (!cartItem.getMember().equals(member)) {
            throw new AccessDeniedException("권한이 없는 장바구니 상품 입니다.");
        }
        cartItem.changeCount(dto.getCount());
        return ServiceCartItemResponse.of(cartItem);
    }
}
