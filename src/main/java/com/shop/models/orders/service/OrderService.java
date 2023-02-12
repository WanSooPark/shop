package com.shop.models.orders.service;

import com.shop.commons.errors.exceptions.NoContentException;
import com.shop.models.orders.domain.Order;
import com.shop.models.orders.infra.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public Order add(Order order) {
        return repository.save(order);
    }

    public Order findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoContentException("유효하지 않은 주문 id 입니다."));
    }
}
