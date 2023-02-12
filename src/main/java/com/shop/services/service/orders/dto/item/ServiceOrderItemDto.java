package com.shop.services.service.orders.dto.item;

import lombok.Data;

public class ServiceOrderItemDto {

    @Data
    public static class Request {
        private Long id;
        private Long count;
        private ServiceOrderItemOptionDto.Request option;
    }

}
