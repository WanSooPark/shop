package com.allddaom.services.service.orders.controller;

import com.allddaom.commons.security.CurrentAccount;
import com.allddaom.models.members.domain.Member;
import com.allddaom.services.service.orders.dto.ServiceOrderCompleteDto;
import com.allddaom.services.service.orders.dto.ServiceOrderFormDto;
import com.allddaom.services.service.orders.dto.ServiceOrderResponse;
import com.allddaom.services.service.orders.dto.ServiceOrdererProfileResponse;
import com.allddaom.services.service.orders.dto.serarch.ServiceOrderSearchDto;
import com.allddaom.services.service.orders.service.ServiceOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class ServiceOrderController {

    private final ServiceOrderService serviceOrderService;

    @GetMapping
    public String orderListView(@CurrentAccount Member member, ServiceOrderSearchDto.Request dto, Model model) {
        return "order/order_list";
    }

    @PostMapping("/form")
    public String getOrderView(@CurrentAccount Member member, ServiceOrderFormDto.Request dto, Model model) {
        ServiceOrderFormDto.Response response = serviceOrderService.getOrderForm(dto);
        model.addAttribute("items", response.getItems());
        ServiceOrdererProfileResponse orderer = null;
        if (ObjectUtils.isEmpty(member)) {
            orderer = ServiceOrdererProfileResponse.anonymous();
        } else {
            orderer = ServiceOrdererProfileResponse.of(member);
        }
        model.addAttribute("orderer", orderer);
        model.addAttribute("totalPrice", response.getTotalPrice());
        model.addAttribute("deliveryCost", response.getDeliveryCost());
        model.addAttribute("totalAmount", response.getTotalAmount());
        model.addAttribute("reserves", response.getReserves());
        return "order/order_form";
    }

    @GetMapping("/complete")
    public String orderCompleteView(@CurrentAccount Member member, ServiceOrderCompleteDto.Request dto, Model model) {
        ServiceOrderResponse order = serviceOrderService.getOrder(dto.getOrderId());
        model.addAttribute("order", order);
        return "order/order_complete";
    }

}
