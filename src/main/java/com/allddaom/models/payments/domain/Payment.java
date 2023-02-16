package com.allddaom.models.payments.domain;

import com.allddaom.commons.entity.BaseEntity;
import com.allddaom.models.orders.domain.Order;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Order order;

    private String tid;
    private String username;
    private String userId;

    private String bankCode;
    private String bankName;

    /* 신용카드 필드 */
    private String trxAmount;
    private String tranDate;
    private String tranTime;
    private String cardCode;
    private String cardName;
    private String cardNo;
    private String quota;
    private String cardAuthNo;

    /* 가상계좌 필드 */
    private String amount;
    private String virtualAccount;
    private String accountHolder;
    private String depositUsername;
    private String userMail;
    private String itemName;
    private String byPassValue;
    private String expireDate;
    private String expireTime;
    private String isCashReceipt;

    /* 계좌이체 필드*/
    private String accountNo; // 출금계좌번호 뒷5자리(앞부분은 *로 표시)
    private String transTime; // 출금처리시간(yyyyMMDDmmHHss)
    private String userPhone;
    private String userEmail;

    private String lastCode;
    private String lastMessage;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentType type;

    private LocalDateTime readyDateTime;
    private LocalDateTime completeDateTime;
    private LocalDateTime issuanceVirtualAccountDateTime;
    private LocalDateTime successDateTime;
    private LocalDateTime cancelDateTime;

    /**
     * 마지막 상태 저장
     */
    public void lastStatus(String lastCode, String lastMessage) {
        this.lastCode = lastCode;
        this.lastMessage = lastMessage;
    }

    /**
     * 결제 준비
     */
    public void ready(String tid, PaymentType paymentType) {
        this.status = PaymentStatus.READY;
        this.tid = tid;
        this.type = paymentType;
        this.readyDateTime = LocalDateTime.now();
    }

    /**
     * 신용카드 결제 성공
     */
    public void successCardPayment(String trxAmount, String tranDate, String tranTime, String cardCode, String cardName, String cardNo, String quota, String cardAuthNo, String username) {
        this.status = PaymentStatus.SUCCESS;
        this.trxAmount = trxAmount;
        this.tranDate = tranDate;
        this.tranTime = tranTime;
        this.cardCode = cardCode;
        this.cardName = cardName;
        this.cardNo = cardNo;
        this.quota = quota;
        this.cardAuthNo = cardAuthNo;
        this.username = username;

        this.successDateTime = LocalDateTime.now();
        this.order.successPayment();
    }

    /**
     * 요청 성공
     * (결제 성공x)
     */
    public void complete() {
        this.status = PaymentStatus.COMPLETE;
        this.successDateTime = LocalDateTime.now();
    }

    /**
     * 가상계좌 발급
     */
    public void issuanceOfVirtualAccount(String tid, String amount, String virtualAccount, String accountHolder, String username, String userId, String userMail, String itemName, String byPassValue, String expireDate, String expireTime, String bankCode, String bankName, String isCashReceipt) {
        this.tid = tid;
        this.amount = amount;
        this.virtualAccount = virtualAccount;
        this.accountHolder = accountHolder;
        this.username = username;
        this.userId = userId;
        this.userMail = userMail;
        this.itemName = itemName;
        this.byPassValue = byPassValue;
        this.expireDate = expireDate;
        this.expireTime = expireTime;
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.isCashReceipt = isCashReceipt;

        this.status = PaymentStatus.ISSUANCE_VIRTUAL_ACCOUNT;
        this.issuanceVirtualAccountDateTime = LocalDateTime.now();
    }

    /**
     * 취소
     */
    public void cancel() {
        this.status = PaymentStatus.CANCELED;
        this.cancelDateTime = LocalDateTime.now();
    }

    /**
     * 계좌이체 성공
     */
    public void successWireTransfer(String tid, String itemName, String amount, String accountNo, String bankCode, String transTime, String username, String userId, String userPhone, String userMail, String byPassValue) {
        this.tid = tid;
        this.itemName = itemName;
        this.amount = amount;
        this.accountNo = accountNo;
        this.bankCode = bankCode;
        this.transTime = transTime;
        this.username = username;
        this.userId = userId;
        this.userPhone = userPhone;
        this.userMail = userMail;
        this.byPassValue = byPassValue;

        this.status = PaymentStatus.SUCCESS;
        this.successDateTime = LocalDateTime.now();
        this.order.successPayment();
    }

    /**
     * 가상계좌 결제 성공
     */
    public void successVirtualAccount(String tid, String amount, String tranDate, String tranTime, String virtualAccount, String accountHolder, String depositUsername, String userId, String userMail, String itemName, String byPassValue, String expireDate, String expireTime, String bankCode, String bankName, String isCashReceipt) {
        this.tid = tid;
        this.amount = amount;
        this.tranDate = tranDate;
        this.tranTime = tranTime;
        this.virtualAccount = virtualAccount;
        this.accountHolder = accountHolder;
        this.depositUsername = depositUsername;
        this.userId = userId;
        this.userMail = userMail;
        this.itemName = itemName;
        this.byPassValue = byPassValue;
        this.expireDate = expireDate;
        this.expireTime = expireTime;
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.isCashReceipt = isCashReceipt;

        this.status = PaymentStatus.SUCCESS;
        this.successDateTime = LocalDateTime.now();
        this.order.successPayment();
    }
}
