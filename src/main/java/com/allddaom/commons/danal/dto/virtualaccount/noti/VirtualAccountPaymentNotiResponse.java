package com.allddaom.commons.danal.dto.virtualaccount.noti;

import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;

@Data
@Builder
public class VirtualAccountPaymentNotiResponse {
    private String returnCode;
    private String returnMsg;
    private String tid;
    private String orderId;
    private String amount;
    private String tranDate;
    private String tranTime;
    private String virtualAccount;
    private String accountHolder;
    private String depositUsername;
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
