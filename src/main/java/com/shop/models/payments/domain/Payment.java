package com.shop.models.payments.domain;

import com.shop.commons.entity.BaseEntity;
import com.shop.models.orders.domain.Order;
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

    /* 신용카드 필드 */
    private String trxAmount;
    private String tranDate;
    private String tranTime;
    private String cardCode;
    private String cardName;
    private String cardNo;
    private String quota;
    private String cardAuthNo;
    private String username;

    /* 가상계좌 필드 */
    private String bankCode;
    private String bankName;
    private String expireDate;
    private String expireTime;
    private String virtualAccount;
    private String isCashReceipt;
    private String virtualAccountAmount;

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

    public void lastStatus(String lastCode, String lastMessage) {
        this.lastCode = lastCode;
        this.lastMessage = lastMessage;
    }

    public void ready(String tid, PaymentType paymentType) {
        this.status = PaymentStatus.READY;
        this.tid = tid;
        this.type = paymentType;
        this.readyDateTime = LocalDateTime.now();
    }

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
        this.order.complete();
    }

    public void complete() {
        this.status = PaymentStatus.COMPLETE;
        this.successDateTime = LocalDateTime.now();
    }

    public void issuanceOfVirtualAccount(String bankCode, String bankName, String expireDate, String expireTime, String virtualAccount, String isCashReceipt, String amount) {
        this.bankCode = bankCode;
        this.bankName = bankName;
        this.expireDate = expireDate;
        this.expireTime = expireTime;
        this.virtualAccount = virtualAccount;
        this.isCashReceipt = isCashReceipt;
        this.virtualAccountAmount = amount;

        this.status = PaymentStatus.ISSUANCE_VIRTUAL_ACCOUNT;
        this.issuanceVirtualAccountDateTime = LocalDateTime.now();
    }
}
