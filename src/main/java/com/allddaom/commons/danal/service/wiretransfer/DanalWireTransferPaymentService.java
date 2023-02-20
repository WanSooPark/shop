package com.allddaom.commons.danal.service.wiretransfer;

import com.allddaom.commons.danal.dto.wiretransfer.complete.WireTransferPaymentCompleteResponse;
import com.allddaom.commons.danal.dto.wiretransfer.ready.WireTransferPaymentReadyRequest;
import com.allddaom.commons.danal.dto.wiretransfer.ready.WireTransferPaymentReadyResponse;
import com.allddaom.models.orders.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class DanalWireTransferPaymentService {

    private final DanalWireTransferPayment danalVirtualAccountPayment;

    public WireTransferPaymentReadyResponse ready(WireTransferPaymentReadyRequest wireTransferPaymentReadyRequest) {
        WireTransferPaymentReadyResponse response = null;
        Map REQ_DATA = wireTransferPaymentReadyRequest.toMap();
        Map RES_DATA = danalVirtualAccountPayment.CallDanalBank(REQ_DATA, false);

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

        return WireTransferPaymentReadyResponse.builder()
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

    public WireTransferPaymentCompleteResponse complete(Order order, Map retMap) {
        String returnCode = (String) retMap.get("RETURNCODE");
        String returnMsg = (String) retMap.get("RETURNMSG");

        /**************************************************
         * 가상계좌 발급전처리 결과
         **************************************************/
        if (!"0000".equals(returnCode)) {
            // returnCode가 없거나 또는 그 결과가 성공이 아니라면 발급요청을 하지 않아야 함.
            System.out.println("Authentication failed. " + returnMsg + "[" + returnCode + "]");
            return WireTransferPaymentCompleteResponse.builder()
                    .returnCode(returnCode)
                    .returnMessage(returnMsg)
                    .build();
        }

        /*[ 필수 데이터 ]***************************************/
        Map REQ_DATA = new HashMap();

        /**************************************************
         * 결제 정보
         **************************************************/
        REQ_DATA.put("TID", retMap.get("TID"));
        REQ_DATA.put("AMOUNT", String.valueOf(order.getFinalAmount()));

        /**************************************************
         * 연동 정보
         **************************************************/
        REQ_DATA.put("TXTYPE", "ISSUEVACCOUNT");
        REQ_DATA.put("SERVICETYPE", "DANALVACCOUNT");

        Map RES_DATA = danalVirtualAccountPayment.CallDanalBank(REQ_DATA, false);

        String RETURNCODE = (String) RES_DATA.get("RETURNCODE"); // 결과코드
        String RETURNMSG = (String) RES_DATA.get("RETURNMSG");


        String TID = "";
        String ORDERID = "";
        String ITEMNAME = "";
        String AMOUNT = "";
        String ACOUNTNO = "";
        String BANKCODE = "";
        String TRANSTIME = "";
        String USERNAME = "";
        String USERID = "";
        String USERPHONE = "";
        String USEREMAIL = "";
        String BYPASSVALUE = "";
        if (RETURNCODE.equals("0000")) {
            TID = (String) RES_DATA.get("TID");
            ORDERID = (String) RES_DATA.get("ORDERID");
            ITEMNAME = (String) RES_DATA.get("ITEMNAME");
            AMOUNT = (String) RES_DATA.get("AMOUNT");
            ACOUNTNO = (String) RES_DATA.get("ACOUNTNO");
            BANKCODE = (String) RES_DATA.get("BANKCODE");
            TRANSTIME = (String) RES_DATA.get("TRANSTIME");
            USERNAME = (String) RES_DATA.get("USERNAME");
            USERID = (String) RES_DATA.get("USERID");
            USERPHONE = (String) RES_DATA.get("USERPHONE");
            USEREMAIL = (String) RES_DATA.get("USEREMAIL");
            BYPASSVALUE = (String) RES_DATA.get("BYPASSVALUE");
        }

        return WireTransferPaymentCompleteResponse.builder()
                .returnCode(RETURNCODE)
                .returnMessage(RETURNMSG)
                .tid(TID)
                .orderId(ORDERID)
                .itemName(ITEMNAME)
                .amount(AMOUNT)
                .accountNo(ACOUNTNO)
                .bankCode(BANKCODE)
                .transTime(TRANSTIME)
                .username(USERNAME)
                .userId(USERID)
                .userPhone(USERPHONE)
                .userMail(USEREMAIL)
                .byPassValue(BYPASSVALUE)
                .build();
    }

}
