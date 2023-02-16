package com.allddaom.services.service.carts.api;

import com.allddaom.commons.security.CurrentAccount;
import com.allddaom.models.members.domain.Member;
import com.allddaom.services.service.carts.dto.ServiceCartResponse;
import com.allddaom.services.service.carts.service.ServiceCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class ServiceCartApi {

    private final ServiceCartService serviceCartService;

    @GetMapping
    public ResponseEntity<?> getCart(@CurrentAccount Member member, HttpServletRequest request) {
        ServiceCartResponse cart = null;
        if (!ObjectUtils.isEmpty(member)) {
            cart = serviceCartService.findCartItemByMember(member);
        } else {
            HttpSession session = request.getSession();
            cart = serviceCartService.findCartItemBySessionId(session.getId());
        }
        return ResponseEntity.ok(cart);
    }

}
