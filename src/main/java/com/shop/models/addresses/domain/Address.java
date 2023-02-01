package com.shop.models.addresses.domain;

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
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private String postcode; // 우편번호

    private String road; // 도로명

    private String jibun; // 지번

    private String detail; // 상세주소

    private String extra; // 참고항목

    private String memo; // 요청사항

    private String receiver; // 수취인

    public String toString() {
        return this.postcode + "_" + this.road + "_" + this.detail;
    }

}
