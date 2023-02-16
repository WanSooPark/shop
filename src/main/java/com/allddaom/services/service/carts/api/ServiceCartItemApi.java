package com.allddaom.services.service.carts.api;

import com.allddaom.commons.security.CurrentAccount;
import com.allddaom.models.members.domain.Member;
import com.allddaom.services.service.carts.dto.ServiceCartItemResponse;
import com.allddaom.services.service.carts.dto.api.ServiceCartItemDto;
import com.allddaom.services.service.carts.service.ServiceCartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/{cartItemId}")
    public ResponseEntity<?> deleteCartItem(@CurrentAccount Member member, @PathVariable Long cartItemId, @RequestBody ServiceCartItemDto.ChangeCountRequest dto) {
        ServiceCartItemResponse cartItem = serviceCartItemService.changeCartItemCount(cartItemId, dto, member);
        return ResponseEntity.ok(cartItem);
    }

}
