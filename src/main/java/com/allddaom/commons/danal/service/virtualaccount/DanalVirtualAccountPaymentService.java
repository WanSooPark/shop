package com.allddaom.commons.danal.service.virtualaccount;

import com.allddaom.commons.danal.dto.virtualaccount.complete.VirtualAccountPaymentCompleteResponse;
import com.allddaom.commons.danal.dto.virtualaccount.noti.VirtualAccountPaymentNotiResponse;
import com.allddaom.commons.danal.dto.virtualaccount.ready.VirtualAccountPaymentReadyRequest;
import com.allddaom.commons.danal.dto.virtualaccount.ready.VirtualAccountPaymentReadyResponse;
import com.allddaom.models.orders.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
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

    /**
     * complete 데이터 바인딩
     * 다날결제창 완료 후
     */
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

    /**
     * 바인딩 받은 complete 데이터 검증
     */
    public VirtualAccountPaymentCompleteResponse complete(Order order, Map retMap) {
        String returnCode = (String) retMap.get("RETURNCODE");
        String returnMsg = (String) retMap.get("RETURNMSG");

        /**************************************************
         * 가상계좌 발급전처리 결과
         **************************************************/
        if (!"0000".equals(returnCode)) {
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
        REQ_DATA.put("TID", retMap.get("TID"));
        REQ_DATA.put("AMOUNT", String.valueOf(order.getFinalAmount()));

        /**************************************************
         * 연동 정보
         **************************************************/
        REQ_DATA.put("TXTYPE", "ISSUEVACCOUNT");
        REQ_DATA.put("SERVICETYPE", "DANALVACCOUNT");

        Map RES_DATA = danalVirtualAccountPayment.CallVAccount(REQ_DATA, false);

        String RETURNCODE = (String) RES_DATA.get("RETURNCODE"); // 결과코드
        String RETURNMSG = (String) RES_DATA.get("RETURNMSG");

        String TID = "";
        String ORDERID = "";
        String AMOUNT = "";
        String VIRTUALACCOUNT = "";
        String ACCOUNTHOLDER = "";
        String USERNAME = "";
        String USERID = "";
        String USERMAIL = "";
        String ITEMNAME = "";
        String BYPASSVALUE = "";
        String EXPIREDATE = "";
        String EXPIRETIME = "";
        String BANKCODE = "";
        String BANKNAME = "";
        String ISCASHRECEIPT = "";
        if (RETURNCODE.equals("0000")) {
            TID = (String) RES_DATA.get("TID");
            ORDERID = (String) RES_DATA.get("ORDERID");
            AMOUNT = (String) RES_DATA.get("AMOUNT");
            VIRTUALACCOUNT = (String) RES_DATA.get("VIRTUALACCOUNT");
            ACCOUNTHOLDER = (String) RES_DATA.get("ACCOUNTHOLDER");
            USERNAME = (String) RES_DATA.get("USERNAME");
            USERID = (String) RES_DATA.get("USERID");
            USERMAIL = (String) RES_DATA.get("USERMAIL");
            ITEMNAME = (String) RES_DATA.get("ITEMNAME");
            BYPASSVALUE = (String) RES_DATA.get("BYPASSVALUE");
            EXPIREDATE = (String) RES_DATA.get("EXPIREDATE");
            EXPIRETIME = (String) RES_DATA.get("EXPIRETIME");
            BANKCODE = (String) RES_DATA.get("BANKCODE");
            BANKNAME = (String) RES_DATA.get("BANKNAME");
            ISCASHRECEIPT = (String) RES_DATA.get("ISCASHRECEIPT");
        }

        return VirtualAccountPaymentCompleteResponse.builder()
                .returnCode(RETURNCODE)
                .returnMessage(RETURNMSG)
                .tid(TID)
                .orderId(ORDERID)
                .amount(AMOUNT)
                .virtualAccount(VIRTUALACCOUNT)
                .accountHolder(ACCOUNTHOLDER)
                .username(USERNAME)
                .userId(USERID)
                .userMail(USERMAIL)
                .itemName(ITEMNAME)
                .byPassValue(BYPASSVALUE)
                .expireDate(EXPIREDATE)
                .expireTime(EXPIRETIME)
                .bankCode(BANKCODE)
                .bankName(BANKNAME)
                .isCashReceipt(ISCASHRECEIPT)
                .build();
    }

    /**
     * 노티 데이터 바인딩
     */
    public VirtualAccountPaymentNotiResponse noti(Map reqMap) {
        /*
         * 결제 통지 데이터는 POST로 수신하며, KEY는 'DATA', VALUE는 '암호화 문자열' 이며 결제 정보 데이터를 포함하고 있습니다.
         * DATA 형식 : aes256( "BILLING_DATA" )
         * BILLING_DATA 형식 : KEY1=urlencode(VALUE1)[&KEY2=urlencode(VALUE2)...]
         *
         * 데이터 처리 방법
         * - POST 변수에서 'DATA'의 value를 읽어 옵니다.
         * - DATA 값을 복호화합니다.
         * - 복호화한 문자열은 결제 완료 정보를 포함하며, =&로 구분되는 key value pair 문자열입니다. (value-urlencoded)
         * - 해당 문자열을 =&로 구분(파싱)하여 사용할 수 있습니다.
         */
        String QueryString = danalVirtualAccountPayment.data2str(reqMap);
        //System.out.println("QueryString: " + QueryString);

        Map resMap = danalVirtualAccountPayment.str2data(QueryString);

        String RES_STR = (String) resMap.get("DATA"); // POST 변수에서 'DATA'의 value를 읽어 옵니다.

        if (RES_STR != null && !"".equals(RES_STR)) {
            RES_STR = danalVirtualAccountPayment.toDecrypt(RES_STR); // urldecode한 value를 복호화합니다.
        } else {
            RES_STR = "Data not found";
        }

        // CPID=9010006839&TID=202302170416571491321450&AMOUNT=200&EXPIREDATE=20230220&EXPIRETIME=235959&BANKCODE=004&BANKNAME=%B1%B9%B9%CE%C0%BA%C7%E0&VIRTUALACCOUNT=73249072334651&ACCOUNTHOLDER=%B9%AE%BA%B4%B7%AE&ISCASHRECEIPT=N&TRANDATE=20230217&TRANTIME=041745&RETURNCODE=0000&RETURNMSG=%BC%BA%B0%F8&DEPOSITUSERNAME=%B9%AE%BA%B4%B7%AE&ITEMNAME=%B3%CA%B1%B8%B8%AE&ORDERID=11&USERID=1

        Map<String, String> map = new HashMap<>();
        String[] fields = RES_STR.split("&");
        Arrays.stream(fields)
                .forEach(field -> {
                    String[] split = field.split("=");
                    map.put(split[0], split[1]);
                });

        String RETURNCODE = map.get("RETURNCODE"); // 결과 값
        String RETURNMSG = map.get("RETURNMSG"); // 결과 메시지
        String TID = map.get("TID"); // 다날 거래 번호
        String ORDERID = map.get("ORDERID"); // CP 주문번호
        String AMOUNT = map.get("AMOUNT"); // 입금 금액
        String TRANDATE = map.get("TRANDATE"); // 입금처리 일자
        String TRANTIME = map.get("TRANTIME"); // 입금처리 시간
        String VIRTUALACCOUNT = map.get("VIRTUALACCOUNT"); // 가상계좌 번호
        String ACCOUNTHOLDER = map.get("ACCOUNTHOLDER"); // CP 명(예금주 명)
        String DEPOSITUSERNAME = map.get("DEPOSITUSERNAME"); // 입금의뢰인 명
        String USERID = map.get("USERID"); // 구매자 ID
        String USERMAIL = map.get("USERMAIL"); // 구매자 EMAIL
        String ITEMNAME = map.get("ITEMNAME"); // 상품명
        String BYPASSVALUE = map.get("BYPASSVALUE"); // 추가필드 값 (ex) Field1=abc;Field2=def; )
        String EXPIREDATE = map.get("EXPIREDATE"); // 입금 마감 기한(YYYYMMDD)
        String EXPIRETIME = map.get("EXPIRETIME"); // 입금 마감 시간
        String BANKCODE = map.get("BANKCODE"); // 은행코드
        String BANKNAME = map.get("BANKNAME"); // 	은행 명
        String ISCASHRECEIPT = map.get("ISCASHRECEIPT"); // 현금영수증 설정 유무(Y / N)

        return VirtualAccountPaymentNotiResponse.builder()
                .returnCode(RETURNCODE)
                .returnMsg(RETURNMSG)
                .tid(TID)
                .orderId(ORDERID)
                .amount(AMOUNT)
                .tranDate(TRANDATE)
                .tranTime(TRANTIME)
                .virtualAccount(VIRTUALACCOUNT)
                .accountHolder(ACCOUNTHOLDER)
                .depositUsername(DEPOSITUSERNAME)
                .userId(USERID)
                .userMail(USERMAIL)
                .itemName(ITEMNAME)
                .byPassValue(BYPASSVALUE)
                .expireDate(EXPIREDATE)
                .expireTime(EXPIRETIME)
                .bankCode(BANKCODE)
                .bankName(BANKNAME)
                .isCashReceipt(ISCASHRECEIPT)
                .build();
    }

}
