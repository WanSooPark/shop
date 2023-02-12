package com.shop.services.service.orders.dto;

import com.shop.models.addresses.domain.Address;
import com.shop.models.members.domain.Member;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceOrdererProfileResponse {

    private Long id;
    private String name;
    private String email;
    private String regionNumber; // 일반 전화 지역번호
    private String generalPhoneNumber1; // 일반 전화
    private String generalPhoneNumber2; // 일반 전화
    private String cellPhoneNumber; // 휴대 전화
    private String postcode; // 우편번호
    private String road; // 도로명
    private String detail; // 상세주소
    private boolean availableReceiveSms;

    public static ServiceOrdererProfileResponse of(Member member) {
        Address address = member.getAddress();
        String postcode = address.getPostcode();
        String road = address.getRoad();
        String detail = address.getDetail();

        return ServiceOrdererProfileResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .regionNumber(member.getRegionNumber())
                .generalPhoneNumber1(member.getGeneralPhoneNumber1())
                .generalPhoneNumber2(member.getGeneralPhoneNumber2())
                .cellPhoneNumber(member.getCellPhoneNumber())
                .postcode(postcode)
                .road(road)
                .detail(detail)
                .availableReceiveSms(member.isAvailableReceiveSms())
                .build();
    }
}
