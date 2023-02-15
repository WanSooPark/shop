package com.shop.services.service.carts.api;

import com.shop.commons.security.CurrentAccount;
import com.shop.models.members.domain.Member;
import com.shop.services.service.carts.dto.ServiceCartResponse;
import com.shop.services.service.carts.service.ServiceCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class ServiceCartApi {

    private final ServiceCartService serviceCartService;

    @GetMapping
    public ResponseEntity<?> getCart(@CurrentAccount Member member) {
        ServiceCartResponse cart = serviceCartService.findCartItemByMember(member);
        return ResponseEntity.ok(cart);
    }

}
