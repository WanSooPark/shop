package com.allddaom.commons.danal.dto.cardpayment.ready;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class CardPaymentReadyRequest {
    private String amount;
    private String orderId;
    private String itemName;
    private String useragent;
    private String name;
    private String userId;
    private String userEmail;
    private String returnUrl;
    private String cancelUrl;
    private String byPassValue; // BYPASSVALUE

    public Map toMap() {
        /*[ 필수 데이터 ]***************************************/
        Map REQ_DATA = new HashMap();

        /******************************************************
         *  RETURNURL 	: CPCGI페이지의 Full URL을 넣어주세요
         *  CANCELURL 	: BackURL페이지의 Full URL을 넣어주세요
         ******************************************************/
//        String RETURNURL = "http://your.domain/CPCGI.jsp";
//        String CANCELURL = "http://your.domain/Cancel.jsp";

        /**************************************************
         * SubCP 정보
         **************************************************/
        REQ_DATA.put("SUBCPID", "");

        /**************************************************
         * 결제 정보
         **************************************************/
        REQ_DATA.put("AMOUNT", this.amount);
        REQ_DATA.put("CURRENCY", "410");
        REQ_DATA.put("ITEMNAME", this.itemName);
        REQ_DATA.put("USERAGENT", this.useragent);
        REQ_DATA.put("ORDERID", this.orderId);
        REQ_DATA.put("OFFERPERIOD", "2015102920151129");

        /**************************************************
         * 고객 정보
         **************************************************/
        REQ_DATA.put("USERNAME", this.name); // 구매자 이름
        REQ_DATA.put("USERID", this.userId); // 사용자 ID
        REQ_DATA.put("USEREMAIL", this.userEmail); // 소보법 email 수신처

        /**************************************************
         * URL 정보
         **************************************************/
        REQ_DATA.put("CANCELURL", this.cancelUrl);
        REQ_DATA.put("RETURNURL", this.returnUrl);

        /**************************************************
         * 기본 정보
         **************************************************/
        REQ_DATA.put("TXTYPE", "AUTH");
        REQ_DATA.put("SERVICETYPE", "DANALCARD");
        REQ_DATA.put("ISNOTI", "N");
        REQ_DATA.put("BYPASSVALUE", this.byPassValue); // BILL 응답 또는 Noti 에서 돌려받을 값. '&'를 사용할 경우 값이 잘리게되므로 유의.
        return REQ_DATA;
    }
}
