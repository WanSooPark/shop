package com.allddaom.services.service.orders.dto.address;

import lombok.Data;

import javax.validation.constraints.NotBlank;

public class ServiceOrderAddressDto {

    @Data
    public static class Request {
        @NotBlank
        private String postcode; // 우편번호
        private String road; // 도로명
        private String detail; // 상세주소
    }

}
