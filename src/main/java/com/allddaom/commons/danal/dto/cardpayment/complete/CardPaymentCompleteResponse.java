package com.allddaom.commons.danal.dto.cardpayment.complete;

import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;

@Data
@Builder
public class CardPaymentCompleteResponse {
    private String returnCode; // 응답 코드
    private String returnMessage; // 응답 메시지
    private String trxAmount; // TRXAMOUNT
    private String tranDate; // TRANDATE
    private String tranTime; // TRANTIME
    private String cardCode; // CARDCODE
    private String cardName; // CARDNAME
    private String cardNo; // CARDNO
    private String quota; // QUOTA
    private String cardAuthNo; // CARDAUTHNO
    private String username; // USERNAME

    public boolean isSuccess() {
        return !ObjectUtils.isEmpty(returnCode) && returnCode.equals("0000");
    }
}
