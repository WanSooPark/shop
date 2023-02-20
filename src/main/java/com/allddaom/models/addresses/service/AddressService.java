package com.allddaom.models.addresses.service;

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
}
