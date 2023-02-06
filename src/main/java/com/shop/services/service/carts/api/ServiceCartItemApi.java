package com.shop.services.service.carts.api;

import com.shop.commons.security.CurrentAccount;
import com.shop.models.members.domain.Member;
import com.shop.services.service.carts.dto.api.ServiceCartItemDto;
import com.shop.services.service.carts.service.ServiceCartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart-items")
@RequiredArgsConstructor
public class ServiceCartItemApi {

    private final ServiceCartItemService serviceCartItemService;

    @PostMapping
    public ResponseEntity<?> addCartItem(@CurrentAccount Member member, @RequestBody ServiceCartItemDto.Request dto) {
        ServiceCartItemDto.Response response = serviceCartItemService.add(dto, member);
        return ResponseEntity.ok(response);
    }

}
