package com.allddaom.models.addresses.domain;

import com.allddaom.models.members.domain.Member;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    private String name; // 주소록 이름

    private String postcode; // 우편번호

    private String road; // 도로명

    private String jibun; // 지번

    private String detail; // 상세주소

    private String extra; // 참고항목

    private String recipientName; // 받는사람 이름

    private String recipientGeneralPhoneNumber; // 받는사람 전화번호

    private String recipientCellPhoneNumber; // 받는사람 휴대폰번호

    private String deliveryMemo; // 배달 메모

    @Override
    public String toString() {
        return this.postcode + "_" + this.road + "_" + this.detail;
    }

}
