package com.allddaom.services.service.orders.api;

import com.allddaom.commons.security.CurrentAccount;
import com.allddaom.models.members.domain.Member;
import com.allddaom.services.service.orders.dto.payment.ServiceOrderPaymentCompleteDto;
import com.allddaom.services.service.orders.dto.payment.ServiceOrderPaymentReadyDto;
import com.allddaom.services.service.orders.service.ServiceOrderPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders/{id}/payment")
@RequiredArgsConstructor
public class ServiceOrderPaymentApi {

    private final ServiceOrderPaymentService serviceOrderPaymentService;

    @Deprecated
    @PostMapping("/ready")
    public ResponseEntity<?> ready(@CurrentAccount Member member, @PathVariable Long id, @RequestBody ServiceOrderPaymentReadyDto.Request dto, Model model) {
        ServiceOrderPaymentReadyDto.Response response = serviceOrderPaymentService.ready(id, dto.getPaymentType(), dto.isMobile(), member);
        return ResponseEntity.ok(response);
    }

    @Deprecated
    @PostMapping("/complete")
    public ResponseEntity<?> complete(@CurrentAccount Member member, @PathVariable Long id, @RequestBody ServiceOrderPaymentCompleteDto.Request dto, Model model) {
        return ResponseEntity.ok(null);
    }

}
