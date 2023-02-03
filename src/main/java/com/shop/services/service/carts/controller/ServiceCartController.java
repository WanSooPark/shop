package com.shop.services.service.carts.controller;//package com.shop.services.service.carts.controller;

import com.shop.commons.security.CurrentAccount;
import com.shop.models.members.domain.Member;
import com.shop.services.service.carts.dto.ServiceCartResponse;
import com.shop.services.service.carts.service.ServiceCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class ServiceCartController {

    private final ServiceCartService cartService;

    @GetMapping
    public String cartView(@CurrentAccount Member member, Model model) {
        ServiceCartResponse cart = cartService.findCartItemByMember(member);
        model.addAttribute("cart", cart);
        return "cart/cart";
    }

}
