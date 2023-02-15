package com.shop.services.service.orders.controller;

import com.shop.commons.security.CurrentAccount;
import com.shop.models.members.domain.Member;
import com.shop.services.service.orders.dto.ServiceOrderCompleteDto;
import com.shop.services.service.orders.dto.ServiceOrderFormDto;
import com.shop.services.service.orders.dto.ServiceOrderResponse;
import com.shop.services.service.orders.dto.ServiceOrdererProfileResponse;
import com.shop.services.service.orders.service.ServiceOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class ServiceOrderController {

    private final ServiceOrderService serviceOrderService;

    @PostMapping("/form")
    public String getOrderView(@CurrentAccount Member member, ServiceOrderFormDto.Request dto, Model model) {
        ServiceOrderFormDto.Response response = serviceOrderService.getOrderForm(dto);
        model.addAttribute("items", response.getItems());
        ServiceOrdererProfileResponse orderer = ServiceOrdererProfileResponse.of(member);
        model.addAttribute("orderer", orderer);
        return "order/order_form";
    }

    @GetMapping("/complete")
    public String orderCompleteView(@CurrentAccount Member member, ServiceOrderCompleteDto.Request dto, Model model) {
        ServiceOrderResponse order = serviceOrderService.getOrder(dto.getOrderId());
        model.addAttribute("order", order);
        return "/order/order_complete";
    }

}
