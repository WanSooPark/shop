package com.allddaom.models.orders.infra.repo;

import com.allddaom.models.orders.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
