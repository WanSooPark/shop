package com.allddaom.models.orders.infra.repo;

import com.allddaom.models.members.domain.Member;
import com.allddaom.models.orders.domain.Order;
import com.allddaom.models.orders.domain.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>, QDSLOrderRepository {
    long countByMemberAndCreatedDateTimeBetweenAndStatusIn(Member member, LocalDateTime startDateTime, LocalDateTime endDateTime, List<OrderStatus> statusList);
}