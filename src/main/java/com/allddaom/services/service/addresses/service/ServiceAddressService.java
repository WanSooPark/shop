package com.allddaom.services.service.addresses.service;

import com.allddaom.models.addresses.domain.Address;
import com.allddaom.models.addresses.service.AddressService;
import com.allddaom.models.members.domain.Member;
import com.allddaom.services.service.addresses.dto.ServiceAddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceAddressService {

    private final AddressService addressService;

    public List<ServiceAddressResponse> findAddresses(Member member) {
        List<Address> addresses = addressService.findByMember(member);
        return addresses.stream()
                .map(ServiceAddressResponse::of)
                .collect(Collectors.toList());
    }

}
