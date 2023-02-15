package com.shop.commons.danal.dto.ready;

import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;

@Data
@Builder
public class CardPaymentReadyResponse {
    private String returnCode; // 응답 코드
    private String returnMessage; // 응답 메시지
    private String startUrl; // 표준페이지 시작 다날 웹서버 URL
    private String startParams; // 표존 페이지에 전달할 params
    private String tid; // 다날 거래 키
    private String orderId; // 가맹점 주문번호
    private String amount; // 결제금액

    public boolean isSuccess() {
        return !ObjectUtils.isEmpty(returnCode) && returnCode.equals("0000");
    }
}
