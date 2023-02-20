package com.allddaom.services.service.addresses.dto;

import com.allddaom.models.addresses.domain.Address;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.ObjectUtils;

@Data
@Builder
public class ServiceAddressResponse {
    private Long id;
    private boolean isDefault;
    private String name;
    private String postcode;
    private String road;
    private String detail;
    private String recipientName; // 받는사람 이름
    private String recipientGeneralPhoneNumber; // 받는사람 전화번호
    private String recipientCellPhoneNumber; // 받는사람 휴대폰번호
    private String deliveryMemo; // 배달 메모

    public static ServiceAddressResponse of(Address address) {
        return ServiceAddressResponse.builder()
                .id(address.getId())
                .isDefault(!ObjectUtils.isEmpty(address.getMember()) && address.getMember()
                        .getAddress()
                        .equals(address))
                .name(ObjectUtils.isEmpty(address.getName()) ? "주소" + address.getId() : address.getName())
                .postcode(address.getPostcode())
                .road(address.getRoad())
                .detail(address.getDetail())
                .recipientName(address.getRecipientName())
                .recipientGeneralPhoneNumber(address.getRecipientGeneralPhoneNumber())
                .recipientCellPhoneNumber(address.getRecipientCellPhoneNumber())
                .deliveryMemo(address.getDeliveryMemo())
                .build();
    }
}
