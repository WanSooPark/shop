package com.allddaom.services.service.carts.service;

import com.allddaom.commons.errors.exceptions.AccessDeniedException;
import com.allddaom.models.carts.domain.CartItem;
import com.allddaom.models.carts.service.CartItemService;
import com.allddaom.models.members.domain.Member;
import com.allddaom.services.service.carts.dto.ServiceCartItemDeleteDto;
import com.allddaom.services.service.carts.dto.ServiceCartResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceCartService {

    private final CartItemService cartItemService;

    public ServiceCartResponse findCartItemByMember(Member member) {
        List<CartItem> cartItems = cartItemService.findByMember(member);
        return ServiceCartResponse.of(cartItems);
    }

    public ServiceCartResponse findCartItemBySessionId(String sessionId) {
        List<CartItem> cartItems = cartItemService.findBySessionId(sessionId);
        return ServiceCartResponse.of(cartItems);
    }

    public void deleteCartItemByMember(ServiceCartItemDeleteDto.Request dto, Member member) {
        List<Long> cartItemIds = dto.getCartItemIds();
        List<CartItem> cartItems = cartItemService.findByIdIn(cartItemIds);
        cartItems.forEach(cartItem -> {
            if (!cartItem.getMember()
                    .equals(member)) {
                throw new AccessDeniedException("권한이 없는 장바구니 상품 입니다.");
            }
            cartItemService.delete(cartItem);
        });
    }

    public void deleteCartItemBySessionId(ServiceCartItemDeleteDto.Request dto, String sessionId) {
        List<Long> cartItemIds = dto.getCartItemIds();
        List<CartItem> cartItems = cartItemService.findByIdIn(cartItemIds);
        cartItems.forEach(cartItem -> {
            if (!cartItem.getSessionId()
                    .equals(sessionId)) {
                throw new AccessDeniedException("권한이 없는 장바구니 상품 입니다.");
            }
            cartItemService.delete(cartItem);
        });
    }
}
