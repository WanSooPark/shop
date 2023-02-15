package com.shop.services.danal.payments.api;

import com.shop.services.danal.payments.dto.VirtualAccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/danal/payment")
@RequiredArgsConstructor
public class DanalPaymentApi {

    @PostMapping("/noti")
    public ResponseEntity<?> danalPaymentNoti(VirtualAccountDto.Request dto) {
        System.out.println("가상계좌 노티");
        System.out.println(dto.getOrderId());
        System.out.println(dto.getPaymentType());
        return ResponseEntity.ok("OK");
    }

}
