package com.shop.commons.danal.dto.virtualaccount.complete;

import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;

@Data
@Builder
public class VirtualAccountPaymentCompleteResponse {
    private String returnCode; // 응답 코드
    private String returnMessage; // 응답 메시지
    private String bankCode;
    private String bankName;
    private String expireDate;
    private String expireTime;
    private String virtualAccount;
    private String isCashReceipt;
    private String amount;

    public boolean isSuccess() {
        return !ObjectUtils.isEmpty(returnCode) && returnCode.equals("0000");
    }
}
