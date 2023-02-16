package com.allddaom.models.addresses.service;

import com.allddaom.models.addresses.infra.repo.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository repository;
}
