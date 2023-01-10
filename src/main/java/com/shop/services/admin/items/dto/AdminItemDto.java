package com.shop.services.admin.items.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

public class AdminItemDto {

    @Data
    @AllArgsConstructor
    public static class Response {
        private Long id;
    }
}
