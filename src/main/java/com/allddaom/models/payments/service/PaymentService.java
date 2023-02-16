package com.allddaom.models.payments.service;

import com.allddaom.models.payments.infra.repo.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;
}
