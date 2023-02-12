package com.shop.models.orders.domain;

import com.shop.commons.entity.BaseEntity;
import com.shop.models.addresses.domain.Address;
import com.shop.models.members.domain.Member;
import lombok.*;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Getter
@Setter
@Entity
@Table(name = "orders")
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long amount; // 주문 금액

    private Long deliveryCost; // 배송비

    private Long reserves; // 적립금

    private Long finalAmount; // 총 결제금액

    @ManyToOne
    private Member member;

    private String ordererName; // 주문자 이름
    private String ordererGeneralPhoneNumber; // 주문자 전화번호
    private String ordererCellPhoneNumber; // 주문자 휴대폰번호
    private boolean ordererAvailableReceiveSms; // 주문 관련 SMS 수신 여부.
    private String ordererEmail; // 주문자 이메일

    private boolean changeShippingAddressToMemberInformation; // 현재 배송지 정보를 회원정보로 저장 여부

    private String recipientName; // 받는사람 이름
    private String recipientGeneralPhoneNumber; // 받는사람 전화번호
    private String recipientCellPhoneNumber; // 받는사람 휴대폰번호
    private String deliveryMemo; // 배달 메모
    private String orderMemo; // 주문 메모

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; // 주문상태

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItem> items;

    private LocalDateTime orderDateTime; // 주문일시
    private LocalDateTime paidDateTime; // 결제일시
    private LocalDateTime cancelDateTime; // 취소일시
    private LocalDateTime exchangeDateTime; // 교환일시
    private LocalDateTime returnDateTime; // 반품일시

    @ManyToOne(cascade = CascadeType.ALL)
    private Address address; // 배송지 주소

    public void setItems(List<OrderItem> orderItems) {
        this.items = orderItems;

        AtomicReference<Long> amount = new AtomicReference<>(0L);
        long deliveryCost = 0;
        long reserves = 0;

        this.items.forEach(item -> {
            item.setOrder(this);

            amount.updateAndGet(v -> v + item.getAmount());
        });

        this.amount = amount.get();
        this.deliveryCost = deliveryCost;
        this.reserves = reserves;
        this.finalAmount = this.amount + this.deliveryCost;
    }

    public String getItemName() {
        String itemName = "";
        if (!ObjectUtils.isEmpty(this.items)) {
            String name = this.items.get(0)
                    .getName();
            itemName += name;
            if (this.items.size() > 1) {
                itemName += "외 " + this.items + "개";
            }
        }
        return itemName;
    }
}
