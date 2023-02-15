package com.shop.commons.danal.dto.virtualaccount.ready;

import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Data
@Builder
public class VirtualAccountPaymentReadyRequest {
    private String amount;
    private String name;
    private String accountHolder;
    private String orderId;
    private String itemName;
    private String expireDate; // 마감기한 (yyyyMMdd)
    private Long expireDatePlus; // 마감기한 오늘부터 + 몇일?
    private String useragent;
    private String userId;
    private String userEmail;
    private String returnUrl;
    private String cancelUrl;
    private String notiUrl;
    private String byPassValue;

    public Map toMap() {
        /*[ 필수 데이터 ]***************************************/
        Map REQ_DATA = new HashMap();

        /******************************************************
         *  RETURNURL 	: ISSUEVACCOUNT페이지의 Full URL을 넣어주세요
         *  CANCELURL 	: BackURL페이지의 Full URL을 넣어주세요
         *	NotiURL     : Noti페이지의 Full URL을 넣어주세요
         ******************************************************/
//        String RETURNURL 	= "https://your.domain.com/ToVAccount/ISSUEVACCOUNT.jsp";
//        String CANCELURL 	= "https://your.domain.com/ToVAccount/Cancel.jsp";
//        String NotiURL  = "https://your.domain.com/ToVAccount/Noti.jsp";

        /**************************************************
         * SubCP 정보
         **************************************************/
        REQ_DATA.put("SUBCPID", "");

        /**************************************************
         * 결제 정보
         **************************************************/
        REQ_DATA.put("AMOUNT", this.amount);
        REQ_DATA.put("ACCOUNTHOLDER", this.accountHolder); // 예금주 명
        REQ_DATA.put("ITEMNAME", this.itemName);
        REQ_DATA.put("EXPIREDATE", LocalDate.now()
                .plusDays(ObjectUtils.isEmpty(expireDatePlus) ? 3 : expireDatePlus)
                .format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        REQ_DATA.put("ORDERID", this.orderId);

        /**************************************************
         * 구매자 정보
         **************************************************/
        REQ_DATA.put("USERNAME", this.name); // 구매자 이름
        REQ_DATA.put("USEREMAIL", this.userEmail);
        REQ_DATA.put("USERID", this.userId);
        REQ_DATA.put("USERAGENT", this.useragent);

        /**************************************************
         * 연동 정보
         **************************************************/

        REQ_DATA.put("RETURNURL", this.returnUrl);
        REQ_DATA.put("NOTIURL", this.notiUrl);
        REQ_DATA.put("CANCELURL", this.cancelUrl);

        REQ_DATA.put("TXTYPE", "AUTH");
        REQ_DATA.put("SERVICETYPE", "DANALVACCOUNT");
//        REQ_DATA.put("BYPASSVALUE", this.byPassValue); // BILL 응답 또는 Noti 에서 돌려받을 값. '&'를 사용할 경우 값이 잘리게되므로 유의.

        /**************************************************
         * 가상계좌 발급요청전문 발송 및 응답처리
         **************************************************/
        return REQ_DATA;
    }
}
