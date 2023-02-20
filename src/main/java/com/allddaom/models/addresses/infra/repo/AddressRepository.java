package com.allddaom.models.addresses.infra.repo;

import com.allddaom.models.addresses.domain.Address;
import com.allddaom.models.members.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByMember(Member member);
}
