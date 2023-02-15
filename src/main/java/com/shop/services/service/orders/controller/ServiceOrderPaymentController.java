package com.shop.services.service.orders.controller;

import com.shop.commons.security.CurrentAccount;
import com.shop.models.members.domain.Member;
import com.shop.services.service.orders.dto.ServiceOrderResponse;
import com.shop.services.service.orders.dto.payment.ServiceOrderPaymentCompleteDto;
import com.shop.services.service.orders.dto.payment.ServiceOrderPaymentReadyDto;
import com.shop.services.service.orders.dto.popup.payment.PaymentCompleteDto;
import com.shop.services.service.orders.dto.popup.payment.PaymentStartDto;
import com.shop.services.service.orders.service.ServiceOrderPaymentService;
import com.shop.services.service.orders.service.ServiceOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/order/payment")
@RequiredArgsConstructor
public class ServiceOrderPaymentController {

    private final ServiceOrderService serviceOrderService;
    private final ServiceOrderPaymentService serviceOrderPaymentService;

    @GetMapping("/start")
    public String paymentView(@CurrentAccount Member member, PaymentStartDto.Request dto, Model model) {
        ServiceOrderResponse order = serviceOrderService.getOrder(dto.getOrderId());
        model.addAttribute("order", order);
        ServiceOrderPaymentReadyDto.Response ready = serviceOrderPaymentService.ready(dto.getOrderId(), dto.getPaymentType(), dto.getUseragent(), member);
        model.addAttribute("ready", ready);
        return "order/payment/payment_start";
    }

    @PostMapping("/complete")
    public String completeView(@CurrentAccount Member member, PaymentCompleteDto.Request dto, Model model) {
        ServiceOrderPaymentCompleteDto.Response response = serviceOrderPaymentService.complete(dto.getRETURNPARAMS());
        model.addAttribute("complete", response);
        return "order/payment/payment_complete";
    }

    @PostMapping("/cancel")
    public String cancelCompleteView(@CurrentAccount Member member, @RequestParam Map map, PaymentCompleteDto.Request dto, Model model) {
//        ServiceOrderPaymentCompleteDto.Response response = serviceOrderPaymentService.complete(dto.getRETURNPARAMS());
//        model.addAttribute("complete", response);
        System.out.println("");
        return "order/payment/payment_complete";
    }

}
