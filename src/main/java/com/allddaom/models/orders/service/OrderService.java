package com.allddaom.models.orders.service;

import com.allddaom.commons.errors.exceptions.NoContentException;
import com.allddaom.models.orders.domain.Order;
import com.allddaom.models.orders.infra.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;

    public Order add(Order order) {
        order = repository.save(order);
        long no = order.getId() % 10; // 주문번호 1의자리수
        LocalDateTime createdDateTime = order.getCreatedDateTime();
        String yyyyMMddHHmmss = createdDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String orderNo = yyyyMMddHHmmss + no;
        order.setOrderNo(orderNo);
        return order;
    }

    public Order findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoContentException("유효하지 않은 주문 id 입니다."));
    }
}
