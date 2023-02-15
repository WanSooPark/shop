package com.shop.models.carts.service;

import com.shop.commons.errors.exceptions.NoContentException;
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

    public CartItem add(CartItem cartItem) {
        return repository.save(cartItem);
    }

    public CartItem findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoContentException("유효하지 않은 장바구나 상품 id 입니다."));
    }

    public void deleteByIds(List<Long> ids) {
        repository.deleteByIdIn(ids);
    }
}
