package com.allddaom.models.orders.infra.repo;

import com.allddaom.models.members.domain.Member;
import com.allddaom.models.orders.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface QDSLOrderRepository {
    Page<Order> search(String statusGroup, LocalDateTime startDateTime, LocalDateTime endDateTime, Member member, Pageable pageable);
}
