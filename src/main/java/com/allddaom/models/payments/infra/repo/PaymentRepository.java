package com.allddaom.models.payments.infra.repo;

import com.allddaom.models.payments.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
