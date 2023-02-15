package com.shop.commons.danal.service.virtualaccount;

import com.shop.commons.danal.dto.virtualaccount.complete.VirtualAccountPaymentCompleteResponse;
import com.shop.commons.danal.dto.virtualaccount.ready.VirtualAccountPaymentReadyRequest;
import com.shop.commons.danal.dto.virtualaccount.ready.VirtualAccountPaymentReadyResponse;
import com.shop.models.orders.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class DanalVirtualAccountPaymentService {

    private final DanalVirtualAccountPayment danalVirtualAccountPayment;

    public VirtualAccountPaymentReadyResponse ready(VirtualAccountPaymentReadyRequest virtualAccountPaymentReadyRequest) {
        VirtualAccountPaymentReadyResponse response = null;
        Map REQ_DATA = virtualAccountPaymentReadyRequest.toMap();
        Map RES_DATA = danalVirtualAccountPayment.CallVAccount(REQ_DATA, false);

        String RETURNCODE = (String) RES_DATA.get("RETURNCODE"); // 결과코드
        String RETURNMSG = "";
        String BackURL = "";
        String STARTURL = "";
        String STARTPARAMS = "";
        String TID = "";
        String ORDERID = "";
        String AMOUNT = "";
        if (RETURNCODE.equals("0000")) {
            RETURNMSG = (String) RES_DATA.get("RETURNMSG"); // 결과메시지
            STARTURL = (String) RES_DATA.get("STARTURL"); // 다날웹서버 URL
            STARTPARAMS = (String) RES_DATA.get("STARTPARAMS"); // 다날웹서버 로 전달해야할 params
            TID = (String) RES_DATA.get("TID"); // 다날 결제 TID
            ORDERID = (String) RES_DATA.get("ORDERID"); // 가맹점 주문번호
            AMOUNT = (String) RES_DATA.get("AMOUNT"); // 결제금액
        } else {
            RETURNMSG = (String) RES_DATA.get("RETURNMSG"); // 결과메시지
            BackURL = "Javascript:self.close()";
        }

        return VirtualAccountPaymentReadyResponse.builder()
                .returnCode(RETURNCODE)
                .returnMessage(RETURNMSG)
                .startUrl(STARTURL)
                .startParams(STARTPARAMS)
                .tid(TID)
                .orderId(ORDERID)
                .amount(AMOUNT)
                .build();
    }

    public Map decodeParams(String returnParams) {
        String RES_STR = danalVirtualAccountPayment.toDecrypt(returnParams);
        Map retMap = danalVirtualAccountPayment.str2data(RES_STR);

        String returnCode = (String) retMap.get("RETURNCODE");
        String returnMsg = (String) retMap.get("RETURNMSG");

        //*****  신용카드 인증결과 확인 *****************
        if (ObjectUtils.isEmpty(returnCode) || !returnCode.equals("0000")) {
            // returnCode가 없거나 또는 그 결과가 성공이 아니라면 실패 처리
            System.out.println("Authentication failed. " + returnMsg + "[" + returnCode + "]");
            return null;
        }
        return retMap;
    }

    public VirtualAccountPaymentCompleteResponse complete(Order order, Map retMap) {
        String returnCode = (String) retMap.get("RETURNCODE");
        String returnMsg = (String) retMap.get("RETURNMSG");

        /**************************************************
         * 가상계좌 발급전처리 결과
         **************************************************/
        if (returnCode == null || !"0000".equals(returnCode)) {
            // returnCode가 없거나 또는 그 결과가 성공이 아니라면 발급요청을 하지 않아야 함.
            System.out.println("Authentication failed. " + returnMsg + "[" + returnCode + "]");
            return VirtualAccountPaymentCompleteResponse.builder()
                    .returnCode(returnCode)
                    .returnMessage(returnMsg)
                    .build();
        }

        /*[ 필수 데이터 ]***************************************/
        Map REQ_DATA = new HashMap();

        /**************************************************
         * 결제 정보
         **************************************************/
        REQ_DATA.put("TID", (String) retMap.get("TID"));
        REQ_DATA.put("AMOUNT", String.valueOf(order.getFinalAmount()));

        /**************************************************
         * 연동 정보
         **************************************************/
        REQ_DATA.put("TXTYPE", "ISSUEVACCOUNT");
        REQ_DATA.put("SERVICETYPE", "DANALVACCOUNT");

        Map RES_DATA = danalVirtualAccountPayment.CallVAccount(REQ_DATA, false);

        String RETURNCODE = (String) RES_DATA.get("RETURNCODE"); // 결과코드
        String RETURNMSG = (String) RES_DATA.get("RETURNMSG");

        String BANKCODE = "";
        String BANKNAME = "";
        String EXPIREDATE = "";
        String EXPIRETIME = "";
        String VIRTUALACCOUNT = "";
        String ISCASHRECEIPT = "";
        String AMOUNT = "";
        if (RETURNCODE.equals("0000")) {
            BANKCODE = (String) RES_DATA.get("BANKCODE");
            BANKNAME = (String) RES_DATA.get("BANKNAME");
            EXPIREDATE = (String) RES_DATA.get("EXPIREDATE");
            EXPIRETIME = (String) RES_DATA.get("EXPIRETIME");
            VIRTUALACCOUNT = (String) RES_DATA.get("VIRTUALACCOUNT");
            ISCASHRECEIPT = (String) RES_DATA.get("ISCASHRECEIPT");
            AMOUNT = (String) RES_DATA.get("AMOUNT");
        }

        return VirtualAccountPaymentCompleteResponse.builder()
                .returnCode(RETURNCODE)
                .returnMessage(RETURNMSG)
                .bankCode(BANKCODE)
                .bankName(BANKNAME)
                .expireDate(EXPIREDATE)
                .expireTime(EXPIRETIME)
                .virtualAccount(VIRTUALACCOUNT)
                .isCashReceipt(ISCASHRECEIPT)
                .amount(AMOUNT)
                .build();
    }

}
