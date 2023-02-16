package com.allddaom.services.service.carts.controller;//package com.shop.services.service.carts.controller;

import com.allddaom.commons.security.CurrentAccount;
import com.allddaom.models.members.domain.Member;
import com.allddaom.services.service.carts.dto.ServiceCartResponse;
import com.allddaom.services.service.carts.service.ServiceCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class ServiceCartController {

    private final ServiceCartService serviceCartService;

    @GetMapping
    public String cartView(@CurrentAccount Member member, HttpServletRequest request, Model model) {
        ServiceCartResponse cart = null;
        if (!ObjectUtils.isEmpty(member)) {
            cart = serviceCartService.findCartItemByMember(member);
        } else {
            HttpSession session = request.getSession();
            cart = serviceCartService.findCartItemBySessionId(session.getId());
        }
        model.addAttribute("cart", cart);
        return "cart/cart";
    }

}
