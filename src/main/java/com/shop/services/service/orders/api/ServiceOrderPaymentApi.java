package com.shop.services.service.orders.api;

import com.shop.commons.security.CurrentAccount;
import com.shop.models.members.domain.Member;
import com.shop.services.service.orders.dto.payment.ServiceOrderPaymentCompleteDto;
import com.shop.services.service.orders.dto.payment.ServiceOrderPaymentNotiDto;
import com.shop.services.service.orders.dto.payment.ServiceOrderPaymentReadyDto;
import com.shop.services.service.orders.service.ServiceOrderPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders/{id}/payment")
@RequiredArgsConstructor
public class ServiceOrderPaymentApi {

    private final ServiceOrderPaymentService serviceOrderPaymentService;

    @PostMapping("/ready")
    public ResponseEntity<?> ready(@CurrentAccount Member member, @PathVariable Long id, @RequestBody ServiceOrderPaymentReadyDto.Request dto, Model model) {
        ServiceOrderPaymentReadyDto.Response response = serviceOrderPaymentService.ready(id, dto.getPaymentType(), dto.getUseragent(), member);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/complete")
    public ResponseEntity<?> complete(@CurrentAccount Member member, @PathVariable Long id, @RequestBody ServiceOrderPaymentCompleteDto.Request dto, Model model) {
        return ResponseEntity.ok(null);
    }

}
