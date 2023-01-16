package com.shop.models.addresses.infra.repo;

import com.shop.models.addresses.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
