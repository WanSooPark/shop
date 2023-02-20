package com.allddaom.services.admin.orders.controller;

import com.allddaom.services.admin.orders.dto.AdminOrderSearchDto;
import com.allddaom.services.admin.orders.service.AdminOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/order")
@RequiredArgsConstructor
public class AdminOrderController {

    private final AdminOrderService adminOrderService;

    @GetMapping
    public String adminOrderListView(@PageableDefault(size = 5) Pageable pageable, AdminOrderSearchDto.Request dto, Model model) {
        AdminOrderSearchDto.Response response = adminOrderService.search(dto, pageable);
        model.addAttribute("orderPage", response.getOrderPage());
        return "admin/order/order_list";
    }
}
