package com.allddaom.models.orders.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class OrderAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String postcode; // 우편번호

    private String road; // 도로명

    private String detail; // 상세주소

    private String recipientName; // 받는사람 이름

    private String recipientGeneralPhoneNumber; // 받는사람 전화번호

    private String recipientCellPhoneNumber; // 받는사람 휴대폰번호

    private String deliveryMemo; // 배달 메모

    public String toString() {
        return this.postcode + "_" + this.road + "_" + this.detail;
    }

}
