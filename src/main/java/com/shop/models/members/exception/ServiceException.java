package com.shop.models.members.exception;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}
