package com.allddaom.models.carts.infra.repo;

import com.allddaom.models.carts.domain.CartItem;
import com.allddaom.models.members.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByMember(Member member);

    void deleteByIdIn(List<Long> ids);

    List<CartItem> findBySessionId(String sessionId);

    List<CartItem> findByIdIn(List<Long> ids);

    Long countByMember(Member member);

    Long countBySessionId(String sessionId);
}