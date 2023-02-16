package com.allddaom.commons.danal.dto.virtualaccount.complete;

import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;

@Data
@Builder
public class VirtualAccountPaymentCompleteResponse {
    private String returnCode; // 응답 코드
    private String returnMessage; // 응답 메시지
    private String tid; // TID
    private String orderId; // ORDERID
    private String amount; // AMOUNT
    private String virtualAccount;
    private String accountHolder;
    private String username;
    private String userId;
    private String userMail;
    private String itemName;
    private String byPassValue;
    private String expireDate;
    private String expireTime;
    private String bankCode;
    private String bankName;
    private String isCashReceipt;

    public boolean isSuccess() {
        return !ObjectUtils.isEmpty(returnCode) && returnCode.equals("0000");
    }
}
