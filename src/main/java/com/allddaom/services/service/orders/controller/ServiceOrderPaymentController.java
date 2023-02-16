package com.allddaom.services.service.orders.controller;

import com.allddaom.commons.security.CurrentAccount;
import com.allddaom.models.members.domain.Member;
import com.allddaom.services.service.orders.dto.ServiceOrderResponse;
import com.allddaom.services.service.orders.dto.payment.ServiceOrderPaymentCancelDto;
import com.allddaom.services.service.orders.dto.payment.ServiceOrderPaymentCompleteDto;
import com.allddaom.services.service.orders.dto.payment.ServiceOrderPaymentReadyDto;
import com.allddaom.services.service.orders.dto.popup.payment.PaymentCancelDto;
import com.allddaom.services.service.orders.dto.popup.payment.PaymentCompleteDto;
import com.allddaom.services.service.orders.dto.popup.payment.PaymentStartDto;
import com.allddaom.services.service.orders.service.ServiceOrderPaymentService;
import com.allddaom.services.service.orders.service.ServiceOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        ServiceOrderPaymentReadyDto.Response ready = serviceOrderPaymentService.ready(dto.getOrderId(), dto.getPaymentType(), dto.isMobile(), member);
        model.addAttribute("ready", ready);
        return "order/payment/payment_start";
    }

    @PostMapping("/{paymentType}/complete")
    public String completeView(@CurrentAccount Member member, @PathVariable String paymentType, PaymentCompleteDto.Request dto, Model model) {
        ServiceOrderPaymentCompleteDto.Response response = serviceOrderPaymentService.complete(paymentType, dto.getRETURNPARAMS());
        model.addAttribute("complete", response);
        return "order/payment/payment_complete";
    }

    @GetMapping("/{paymentType}/cancel")
    public String cancelCompleteView(@CurrentAccount Member member, @PathVariable String paymentType, PaymentCancelDto.Request dto, Model model) {
        ServiceOrderPaymentCancelDto.Response response = serviceOrderPaymentService.cancel(dto.getOrderId(), paymentType);
        model.addAttribute("complete", response);
        return "order/payment/payment_complete";
    }

}
