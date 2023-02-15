package com.shop.commons.danal.dto.wiretransfer.ready;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class WireTransferPaymentReadyRequest {
    private String amount;
    private String name;
    private String orderId;
    private String itemName;
    private String useragent;
    private String userId;
    private String userEmail;

    private String returnUrl;
    private String cancelUrl;
    private String byPassValue;

    public Map toMap() {
        Map REQ_DATA = new HashMap();

        /******************************************************
         *  RETURNURL 	: CPCGI페이지의 Full URL을 넣어주세요
         *  CANCELURL 	: 사용자가 결제 도중에 '취소'버튼을 눌렀을 때 돌아갈 페이지의 Full URL을 넣어주세요
         ******************************************************/
//        String RETURNURL = "https://your.domain.com/ToDanalBank/CPCGI.jsp";
//        String CANCELURL = "https://your.domain.com/ToDanalBank/Cancel.jsp";

        /**************************************************
         * 결제 정보
         **************************************************/
        REQ_DATA.put("ORDERID", this.orderId);
        REQ_DATA.put("AMOUNT", this.amount);
        REQ_DATA.put("ITEMNAME", this.itemName);
        REQ_DATA.put("BYPASSVALUE", this.byPassValue);

        /**************************************************
         * 구매자 정보
         **************************************************/
        REQ_DATA.put("USERNAME", this.name);
        REQ_DATA.put("USERID", this.userId);
        REQ_DATA.put("USEREMAIL", this.userEmail);
        REQ_DATA.put("USERAGENT", this.useragent);

        /**************************************************
         * 연동 정보
         **************************************************/
        REQ_DATA.put("CANCELURL", this.cancelUrl);
        REQ_DATA.put("RETURNURL", this.returnUrl);
        REQ_DATA.put("ISNOTI", "N");

        REQ_DATA.put("TXTYPE", "AUTH");
        REQ_DATA.put("SERVICETYPE", "WIRETRANSFER");

        /**************************************************
         * 계좌이체 시작요청전문 발송 및 응답처리
         **************************************************/
        return REQ_DATA;
    }
}
