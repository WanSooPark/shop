package com.shop.services.service.orders.dto;

import com.shop.services.service.orders.form.OrderItemFormResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

public class ServiceOrderFormDto {

    @Data
    public static class Request {
        private Long[] cartItemIds;
        private Long[] cartItemCounts;
    }

    @Data
    @Builder
    public static class Response {
        private List<OrderItemFormResponse> items;
    }

}
