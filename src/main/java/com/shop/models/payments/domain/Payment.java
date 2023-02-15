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

    private String trxAmount;
    private String tranDate;
    private String tranTime;
    private String cardCode;
    private String cardName;
    private String cardNo;
    private String quota;
    private String cardAuthNo;
    private String username;

    private String lastCode;
    private String lastMessage;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentType type;

    private LocalDateTime readyDateTime;
    private LocalDateTime completeDateTime;
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

    public void success(String trxAmount, String tranDate, String tranTime, String cardCode, String cardName, String cardNo, String quota, String cardAuthNo, String username) {
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
}
