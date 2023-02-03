package com.shop.models.carts.infra.repo;

import com.shop.models.carts.domain.CartItem;
import com.shop.models.members.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByMember(Member member);
}