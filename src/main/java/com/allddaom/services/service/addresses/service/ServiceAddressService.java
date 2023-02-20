package com.allddaom.services.service.addresses.service;

import com.allddaom.commons.errors.exceptions.AccessDeniedException;
import com.allddaom.models.addresses.domain.Address;
import com.allddaom.models.addresses.service.AddressService;
import com.allddaom.models.addresses.service.event.ChangeDefaultAddressEvent;
import com.allddaom.models.members.domain.Member;
import com.allddaom.services.service.addresses.dto.ServiceAddressResponse;
import com.allddaom.services.service.addresses.dto.form.ServiceAddressForm;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceAddressService {

    private final AddressService addressService;
    private final ApplicationEventPublisher eventPublisher;

    public List<ServiceAddressResponse> findAddresses(Member member) {
        List<Address> addresses = addressService.findByMember(member);
        return addresses.stream()
                .map(ServiceAddressResponse::of)
                .collect(Collectors.toList());
    }

    public ServiceAddressForm getAddressForm(Long id, Member member) {
        Address address = addressService.findById(id);
        if (!address.getMember()
                .equals(member)) {
            throw new AccessDeniedException("권한이 없는 주소 id 입니다.");
        }
        return ServiceAddressForm.of(address);
    }

    public ServiceAddressResponse add(ServiceAddressForm form, Member member) {
        Address address = form.toEntity(member);
        if (form.isDefaultAddress()) {
            eventPublisher.publishEvent(ChangeDefaultAddressEvent.builder()
                    .member(member)
                    .name(address.getName())
                    .postcode(address.getPostcode())
                    .road(address.getRoad())
                    .detail(address.getDetail())
                    .recipientName(address.getRecipientName())
                    .recipientGeneralPhoneNumber(address.getRecipientGeneralPhoneNumber())
                    .recipientCellPhoneNumber(address.getRecipientCellPhoneNumber())
                    .deliveryMemo(address.getDeliveryMemo())
                    .build());
        }

        address = addressService.add(address);
        return ServiceAddressResponse.of(address);
    }

    public ServiceAddressResponse update(ServiceAddressForm form, Member member) {
        Address address = addressService.findById(form.getId());

        address = form.toEntity(member);
        if (form.isDefaultAddress()) {
            eventPublisher.publishEvent(ChangeDefaultAddressEvent.builder()
                    .member(member)
                    .name(address.getName())
                    .postcode(address.getPostcode())
                    .road(address.getRoad())
                    .detail(address.getDetail())
                    .recipientName(address.getRecipientName())
                    .recipientGeneralPhoneNumber(address.getRecipientGeneralPhoneNumber())
                    .recipientCellPhoneNumber(address.getRecipientCellPhoneNumber())
                    .deliveryMemo(address.getDeliveryMemo())
                    .build());
        }

        address = addressService.add(address);
        return ServiceAddressResponse.of(address);
    }
}
