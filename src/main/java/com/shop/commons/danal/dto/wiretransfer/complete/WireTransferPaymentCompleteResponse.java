package com.shop.commons.danal.dto.wiretransfer.complete;

import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;

@Data
@Builder
public class WireTransferPaymentCompleteResponse {
    private String returnCode; // 응답 코드
    private String returnMessage; // 응답 메시지

    private String accountNo; // 출금계좌번호 뒷5자리(앞부분은 *로 표시)
    private String bankCode; // 출금은행코드
    private String transTime; // 출금처리시간(yyyyMMDDmmHHss)
    private String username;
    private String userId;
    private String userPhone;
    private String userEmail;

    public boolean isSuccess() {
        return !ObjectUtils.isEmpty(returnCode) && returnCode.equals("0000");
    }
}
