package com.allddaom.services.service.carts.dto;

import lombok.Data;

import java.util.List;

public class ServiceCartItemDeleteDto {

    @Data
    public static class Request {
        private List<Long> cartItemIds;
    }
}
