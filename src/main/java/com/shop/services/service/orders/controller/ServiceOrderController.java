package com.shop.services.service.orders.controller;

import com.shop.commons.security.CurrentAccount;
import com.shop.models.members.domain.Member;
import com.shop.services.service.orders.dto.ServiceOrderDto;
import com.shop.services.service.orders.service.ServiceOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class ServiceOrderController {

    private final ServiceOrderService serviceOrderService;

    @PostMapping("/form")
    public String getOrderView(@CurrentAccount Member member, ServiceOrderDto.Request dto, Model model) {
        System.out.println("");
        return "order/order_form";
    }

}
