package com.allddaom.models.members.infra.repo;

import com.allddaom.models.members.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);

    Member findByUsername(String username);
}