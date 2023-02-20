package com.allddaom.models.addresses.service.event;

import com.allddaom.models.addresses.domain.Address;
import com.allddaom.models.members.domain.Member;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChangeDefaultAddressEvent {
    private Member member;
    private String postcode; // 우편번호
    private String road; // 도로명
    private String detail; // 상세주소
    private String recipientName; // 받는사람 이름
    private String recipientGeneralPhoneNumber; // 받는사람 전화번호
    private String recipientCellPhoneNumber; // 받는사람 휴대폰번호
    private String deliveryMemo; // 배달 메모

    public Address getAddress() {
        Address address = new Address();
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
