package com.shop.commons.danal.service.cardpayment;

import com.shop.commons.danal.dto.cardpayment.complete.CardPaymentCompleteResponse;
import com.shop.commons.danal.dto.cardpayment.ready.CardPaymentReadyRequest;
import com.shop.commons.danal.dto.cardpayment.ready.CardPaymentReadyResponse;
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
public class DanalCardPaymentService {

    private final DanalCardPayment danalCardPayment;

    public CardPaymentReadyResponse ready(CardPaymentReadyRequest cardPaymentReadyRequest) {
        CardPaymentReadyResponse response = null;
        Map REQ_DATA = cardPaymentReadyRequest.toMap();
        Map RES_DATA = danalCardPayment.CallCredit(REQ_DATA, false);

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

        return CardPaymentReadyResponse.builder()
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
        String RES_STR = danalCardPayment.toDecrypt(returnParams);
        Map retMap = danalCardPayment.str2data(RES_STR);

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

    public CardPaymentCompleteResponse complete(Order order, Map retMap) {
        String returnCode = (String) retMap.get("RETURNCODE");
        String returnMsg = (String) retMap.get("RETURNMSG");

        //*****  신용카드 인증결과 확인 *****************
        if (ObjectUtils.isEmpty(returnCode) || !returnCode.equals("0000")) {
            // returnCode가 없거나 또는 그 결과가 성공이 아니라면 실패 처리
            System.out.println("Authentication failed. " + returnMsg + "[" + returnCode + "]");
            return CardPaymentCompleteResponse.builder()
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
        REQ_DATA.put("AMOUNT", String.valueOf(order.getFinalAmount())); // 최초 결제요청(AUTH)시에 보냈던 금액과 동일한 금액을 전송

        /**************************************************
         * 기본 정보
         **************************************************/
        REQ_DATA.put("TXTYPE", "BILL");
        REQ_DATA.put("SERVICETYPE", "DANALCARD");

        Map RES_DATA = danalCardPayment.CallCredit(REQ_DATA, false);

        String RETURNCODE = (String) RES_DATA.get("RETURNCODE"); // 결과코드
        String RETURNMSG = (String) RES_DATA.get("RETURNMSG");
        String TRXAMOUNT = ""; // 실제 승인 가격
        String TRANDATE = ""; // 승인 일자 (yyyyMMdd)
        String TRANTIME = ""; // 승인 시간 (HHmmss)
        String CARDCODE = ""; // 카드사 코드
        String CARDNAME = ""; // 카드사 명
        String CARDNO = ""; // 카드번호 (1111-1111-****-1111 마스킹 처리)
        String QUOTA = ""; // 할부 개월 수 (일시불: "00", 할부 : "02"~"36")
        String CARDAUTHNO = ""; // 거래 승인 번호
        String USERNAME = ""; // 구매자 이름
        if (RETURNCODE.equals("0000")) {
            TRXAMOUNT = (String) RES_DATA.get("TRXAMOUNT");
            TRANDATE = (String) RES_DATA.get("TRANDATE");
            TRANTIME = (String) RES_DATA.get("TRANTIME");
            CARDCODE = (String) RES_DATA.get("CARDCODE");
            CARDNAME = (String) RES_DATA.get("CARDNAME");
            CARDNO = (String) RES_DATA.get("CARDNO");
            QUOTA = (String) RES_DATA.get("QUOTA");
            CARDAUTHNO = (String) RES_DATA.get("CARDAUTHNO");
            USERNAME = (String) RES_DATA.get("USERNAME");
        }

        return CardPaymentCompleteResponse.builder()
                .returnCode(RETURNCODE)
                .returnMessage(RETURNMSG)
                .trxAmount(TRXAMOUNT)
                .tranDate(TRANDATE)
                .tranTime(TRANTIME)
                .cardCode(CARDCODE)
                .cardName(CARDNAME)
                .cardNo(CARDNO)
                .quota(QUOTA)
                .cardAuthNo(CARDAUTHNO)
                .username(USERNAME)
                .build();
    }

}
