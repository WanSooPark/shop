package com.allddaom.services.admin.orders.service;

import com.allddaom.commons.entity.BasePage;
import com.allddaom.models.orders.domain.Order;
import com.allddaom.models.orders.service.OrderService;
import com.allddaom.services.admin.orders.dto.AdminOrderResponse;
import com.allddaom.services.admin.orders.dto.AdminOrderSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminOrderService {

    private final OrderService orderService;

    public AdminOrderSearchDto.Response search(AdminOrderSearchDto.Request dto, Pageable pageable) {
        Page<Order> orderPage = orderService.searchForAdmin(dto.getStatusGroup(), dto.getStartDateTime(), dto.getEndDateTime(), pageable);
        Page<AdminOrderResponse> orderResponsePage = orderPage.map(AdminOrderResponse::of);
        return AdminOrderSearchDto.Response.builder()
                .orderPage(new BasePage<>(orderResponsePage))
                .build();
    }
}
