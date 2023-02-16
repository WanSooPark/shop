package com.allddaom.services.danal.payments.api;

import com.allddaom.services.danal.payments.service.DanalPaymentNotiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/danal/payment")
@RequiredArgsConstructor
public class DanalPaymentApi {

    private final DanalPaymentNotiService danalPaymentNotiService;

    @PostMapping("/virtual-account/noti")
    public ResponseEntity<?> danalPaymentNoti(@Param("orderId") Long orderId, HttpServletRequest request) {
        log.info("다날 가상계좌 노티, orderId=" + orderId);

        // danal virtual account function getReqMap()
        Map reqMap = request.getParameterMap();
        Map retMap = new HashMap();

        Set entSet = reqMap.entrySet();
        Iterator it = entSet.iterator();
        while (it.hasNext()) {
            Map.Entry et = (Map.Entry) it.next();
            Object v = et.getValue();
            if (v instanceof String) {
                String tt = (String) v;
                retMap.put(et.getKey(), tt);
            } else if (v instanceof String[]) {
                String[] tt = (String[]) v;
                retMap.put(et.getKey(), tt[0]);
            } else {
                retMap.put(et.getKey(), v.toString());
            }
        }

        danalPaymentNotiService.notiVirtualAccount(retMap);
        return ResponseEntity.ok("OK");
    }

}
