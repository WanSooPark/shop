package com.allddaom.services.danal.payments.api;

import com.allddaom.services.danal.payments.dto.VirtualAccountDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/danal/payment")
@RequiredArgsConstructor
public class DanalPaymentApi {

    @PostMapping("/virtual-account/noti")
    public ResponseEntity<?> danalPaymentNoti(VirtualAccountDto.Request dto) {
        log.info("다날 가상계좌 노티");
        log.info(dto.toString());
        return ResponseEntity.ok("OK");
    }

}
