package com.allddaom.services.service.carts.api;

import com.allddaom.commons.security.CurrentAccount;
import com.allddaom.models.members.domain.Member;
import com.allddaom.services.service.carts.dto.ServiceCartItemResponse;
import com.allddaom.services.service.carts.dto.api.ServiceCartItemDto;
import com.allddaom.services.service.carts.service.ServiceCartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/cart-items")
@RequiredArgsConstructor
public class ServiceCartItemApi {

    private final ServiceCartItemService serviceCartItemService;

    @PostMapping
    public ResponseEntity<?> addCartItem(@CurrentAccount Member member, HttpServletRequest request, @RequestBody ServiceCartItemDto.Request dto) {
        ServiceCartItemDto.Response response = null;
        if (!ObjectUtils.isEmpty(member)) {
            response = serviceCartItemService.add(dto, member);
        } else {
            HttpSession session = request.getSession();
            response = serviceCartItemService.addBySessionId(dto, session.getId());
        }
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{cartItemId}")
    public ResponseEntity<?> deleteCartItem(@CurrentAccount Member member, HttpServletRequest request, @PathVariable Long cartItemId, @RequestBody ServiceCartItemDto.ChangeCountRequest dto) {
        ServiceCartItemResponse cartItem = null;
        if (!ObjectUtils.isEmpty(member)) {
            cartItem = serviceCartItemService.changeCartItemCount(cartItemId, dto, member);
        } else {
            HttpSession session = request.getSession();
            cartItem = serviceCartItemService.changeCartItemCountBySessionId(cartItemId, dto, session.getId());
        }

        return ResponseEntity.ok(cartItem);
    }

}
