package com.shop.commons.danal.service;

import com.shop.commons.danal.dto.CardPaymentReadyRequest;
import com.shop.commons.danal.dto.CardPaymentReadyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class DanalCardPaymentService {

    private final DanalCardPayment danalCardPayment;

    public CardPaymentReadyResponse ready(CardPaymentReadyRequest cardPaymentReadyRequest) {
        CardPaymentReadyResponse response = null;
        Map REQ_DATA = cardPaymentReadyRequest.toMap();
        Map RES_DATA = danalCardPayment.CallCredit(REQ_DATA, false);

        String RETURNCODE = (String) RES_DATA.get("RETURNCODE"); // 결과코드
        if (RETURNCODE.equals("0000")) {
            Object STARTPARAMS = RES_DATA.get("STARTPARAMS");

            response = CardPaymentReadyResponse.builder()
                    .build();
        } else {
            String RETURNMSG = (String) RES_DATA.get("RETURNMSG"); // 결과메시지
            String BackURL = "Javascript:self.close()";

            String STARTURL = (String) RES_DATA.get("STARTURL"); // 다날웹서버 URL
            String STARTPARAMS = (String) RES_DATA.get("STARTPARAMS"); // 다날웹서버 로 전달해야할 params

            String TID = (String) RES_DATA.get("TID"); // 다날 결제 TID
            String ORDERID = (String) RES_DATA.get("ORDERID"); // 가맹점 주문번호
            String AMOUNT = (String) RES_DATA.get("AMOUNT"); // 결제금액

            response = CardPaymentReadyResponse.builder()
                    .returnCode(RETURNCODE)
                    .returnMessage(RETURNMSG)
                    .startUrl(STARTURL)
                    .startParams(STARTPARAMS)
                    .tid(TID)
                    .orderId(ORDERID)
                    .amount(AMOUNT)
                    .build();
        }

        return response;
    }
}
