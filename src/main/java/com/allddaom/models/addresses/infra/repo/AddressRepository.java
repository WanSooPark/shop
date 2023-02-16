package com.allddaom.models.addresses.infra.repo;

import com.allddaom.models.addresses.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
