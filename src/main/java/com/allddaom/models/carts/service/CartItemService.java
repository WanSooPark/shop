package com.allddaom.models.carts.service;

import com.allddaom.commons.errors.exceptions.NoContentException;
import com.allddaom.models.carts.domain.CartItem;
import com.allddaom.models.carts.infra.repo.CartItemRepository;
import com.allddaom.models.members.domain.Member;
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

    public List<CartItem> findBySessionId(String sessionId) {
        return repository.findBySessionId(sessionId);
    }

    public void delete(CartItem cartItem) {
        repository.delete(cartItem);
    }

    public List<CartItem> findByIdIn(List<Long> ids) {
        return repository.findByIdIn(ids);
    }

    public Long countByMember(Member member) {
        return repository.countByMember(member);
    }

    public Long countBySessionId(String sessionId) {
        return repository.countBySessionId(sessionId);
    }

}
