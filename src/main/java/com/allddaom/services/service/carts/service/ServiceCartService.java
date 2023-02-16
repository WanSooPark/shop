package com.allddaom.services.service.carts.service;

import com.allddaom.models.carts.domain.CartItem;
import com.allddaom.models.carts.service.CartItemService;
import com.allddaom.models.members.domain.Member;
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
}
