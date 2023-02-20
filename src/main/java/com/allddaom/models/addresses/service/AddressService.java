package com.allddaom.models.addresses.service;

import com.allddaom.commons.errors.exceptions.NoContentException;
import com.allddaom.models.addresses.domain.Address;
import com.allddaom.models.addresses.infra.repo.AddressRepository;
import com.allddaom.models.members.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository repository;

    public List<Address> findByMember(Member member) {
        return repository.findByMember(member);
    }

    public Address findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoContentException("유효하지 않은 주소 id 입니다."));
    }

    public Address add(Address address) {
        return repository.save(address);
    }
}
