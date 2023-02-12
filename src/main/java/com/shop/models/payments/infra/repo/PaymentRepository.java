package com.shop.models.payments.infra.repo;

import com.shop.models.payments.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
