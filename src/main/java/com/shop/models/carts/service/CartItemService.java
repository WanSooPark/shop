package com.shop.models.carts.service;

import com.shop.models.carts.domain.CartItem;
import com.shop.models.carts.infra.repo.CartItemRepository;
import com.shop.models.members.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository repository;

    public List<CartItem> findByMember(Member member) {
        return repository.findByMember(member);
    }
}