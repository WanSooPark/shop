package com.shop.services.danal.payments.dto;

import lombok.Data;

public class VirtualAccountDto {

    @Data
    public static class Request {
        private Long orderId;
        private String paymentType;

        private String RETURNCODE; //	Required	4	결과 값
        private String RETURNMSG; //	Required	-	결과 메시지
        private String TID; //	Required	24	다날 거래 번호
        private String ORDERID; //	Optional	255	CP 주문번호
        private String AMOUNT; //	Optional	9	입금 금액
        private String TRANDATE; //	Optional	8	입금처리 일자
        private String TRANTIME; //	Optional	6	입금처리 시간
        private String VIRTUALACCOUNT; //	Optional	16	가상계좌 번호
        private String ACCOUNTHOLDER; //	Optional	10	CP 명(예금주 명)
        private String DEPOSITUSERNAME; //	Optional	10	입금의뢰인 명
        private String USERID; //	Optional	128	구매자 ID
        private String USERMAIL; //	Optional	128	구매자 EMAIL
        private String ITEMNAME; //	Optional	255	상품명
        private String BYPASSVALUE; //	Optional	255	추가필드 값 (ex) Field1=abc;Field2=def; )
        private String EXPIREDATE; //	Optional	8	입금 마감 기한(YYYYMMDD)
        private String EXPIRETIME; //	Optional	6	입금 마감 시간
        private String BANKCODE; //	Optional	3	은행코드
        private String BANKNAME; //	Optional	20	은행 명
        private String ISCASHRECEIPT; //	Optional	1	현금영수증 설정 유무(Y / N)
    }
}
