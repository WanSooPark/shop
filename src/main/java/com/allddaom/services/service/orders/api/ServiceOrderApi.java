package com.allddaom.services.service.orders.api;

import com.allddaom.commons.security.CurrentAccount;
import com.allddaom.models.members.domain.Member;
import com.allddaom.services.service.orders.dto.ServiceOrderDto;
import com.allddaom.services.service.orders.service.ServiceOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class ServiceOrderApi {

    private final ServiceOrderService serviceOrderService;

    @PostMapping
    public ResponseEntity<?> newOrder(@CurrentAccount Member member, @Valid @RequestBody ServiceOrderDto.Request dto, Model model) {
        ServiceOrderDto.Response response = serviceOrderService.newOrder(dto, member);
        return ResponseEntity.ok(response);
    }

}