package com.allddaom.services.service.addresses.dto.form;

import com.allddaom.models.addresses.domain.Address;
import com.allddaom.models.members.domain.Member;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;

@Data
@Builder
public class ServiceAddressForm {
    private Long id;
    private String name; // 주소록 이름
    private String postcode; // 우편번호
    private String road; // 도로명
    private String detail; // 상세주소
    private String recipientName; // 받는사람 이름
    private String recipientGeneralPhoneNumber; // 받는사람 전화번호
    private String recipientCellPhoneNumber; // 받는사람 휴대폰번호
    private String deliveryMemo; // 배달 메모

    private boolean defaultAddress; // 현재 배송지 정보를 회원정보로 저장 여부

    public static ServiceAddressForm of(Address address) {
        return ServiceAddressForm.builder()
                .id(address.getId())
                .name(address.getName())
                .postcode(address.getPostcode())
                .road(address.getRoad())
                .detail(address.getDetail())
                .recipientName(address.getRecipientName())
                .recipientGeneralPhoneNumber(address.getRecipientGeneralPhoneNumber())
                .recipientCellPhoneNumber(address.getRecipientCellPhoneNumber())
                .deliveryMemo(address.getDeliveryMemo())
                .defaultAddress(!ObjectUtils.isEmpty(address.getMember()) && address.getMember()
                        .getAddress()
                        .equals(address))
                .build();
    }

    public static ServiceAddressForm empty() {
        return ServiceAddressForm.builder()
                .id(0L)
                .build();
    }

    public Address toEntity(Member member) {
        Address address = new Address();
        address.setId(this.id);
        address.setMember(member);
        address.setName(this.name);
        address.setPostcode(this.postcode);
        address.setRoad(this.road);
        address.setDetail(this.detail);
        address.setRecipientName(this.recipientName);
        address.setRecipientGeneralPhoneNumber(this.recipientGeneralPhoneNumber);
        address.setRecipientCellPhoneNumber(this.recipientCellPhoneNumber);
        address.setDeliveryMemo(this.deliveryMemo);
        return address;
    }
}
