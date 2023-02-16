package com.allddaom.models.orders.infra.repo;

import com.allddaom.models.orders.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}