package com.allddaom.commons.interceptor.service;

import com.allddaom.models.carts.service.CartItemService;
import com.allddaom.models.members.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceInterceptorCartService {

    private final CartItemService cartItemService;

    public Long getCartItemCountByMember(Member member) {
        return cartItemService.countByMember(member);
    }

    public Long getCartItemCountBySessionId(String sessionId) {
        return cartItemService.countBySessionId(sessionId);
    }
}
