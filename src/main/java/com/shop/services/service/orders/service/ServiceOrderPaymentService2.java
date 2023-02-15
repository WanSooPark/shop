package com.shop.services.service.orders.service;

import com.shop.models.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceOrderPaymentService2 {

    private final OrderService orderService;
}
