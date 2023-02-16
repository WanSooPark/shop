package com.allddaom.services.service.carts.service;

import com.allddaom.commons.errors.exceptions.AccessDeniedException;
import com.allddaom.commons.errors.exceptions.NoContentException;
import com.allddaom.models.carts.domain.CartItem;
import com.allddaom.models.carts.domain.CartItemOption;
import com.allddaom.models.carts.service.CartItemService;
import com.allddaom.models.items.domain.Item;
import com.allddaom.models.items.domain.ItemOption;
import com.allddaom.models.items.service.ItemOptionService;
import com.allddaom.models.items.service.ItemService;
import com.allddaom.models.members.domain.Member;
import com.allddaom.services.service.carts.dto.ServiceCartItemResponse;
import com.allddaom.services.service.carts.dto.api.ServiceCartItemDto;
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

    /**
     * 상품 장바구니 담기
     * 회원 전용
     */
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

    /**
     * 상품 장바구니 담기
     * 비회원 전용
     */
    public ServiceCartItemDto.Response addBySessionId(ServiceCartItemDto.Request dto, String sessionId) {
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
        cartItem.setSessionId(sessionId);

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

    /**
     * 장바구니 상품 수정
     * 회원용
     */
    public ServiceCartItemResponse changeCartItemCount(Long cartItemId, ServiceCartItemDto.ChangeCountRequest dto, Member member) {
        CartItem cartItem = cartItemService.findById(cartItemId);

        if (!cartItem.getMember()
                .equals(member)) {
            throw new AccessDeniedException("권한이 없는 장바구니 상품 입니다.");
        }
        cartItem.changeCount(dto.getCount());
        return ServiceCartItemResponse.of(cartItem);
    }

    /**
     * 장바구니 상품 수정
     * 비회원용
     */
    public ServiceCartItemResponse changeCartItemCountBySessionId(Long cartItemId, ServiceCartItemDto.ChangeCountRequest dto, String sessionId) {
        CartItem cartItem = cartItemService.findById(cartItemId);

        if (!cartItem.getSessionId()
                .equals(sessionId)) {
            throw new AccessDeniedException("권한이 없는 장바구니 상품 입니다.");
        }
        cartItem.changeCount(dto.getCount());
        return ServiceCartItemResponse.of(cartItem);
    }
}
