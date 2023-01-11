package com.shop.commons.errors.exceptions;

import com.shop.commons.errors.ErrorCode;
import org.springframework.validation.Errors;

public class BadRequestException extends BusinessException {

    public BadRequestException(String message) {
        super(message, ErrorCode.BAD_REQUEST_EXCEPTION);
    }

    public BadRequestException(String message, Errors errors) {
        super(message, ErrorCode.BAD_REQUEST_EXCEPTION, errors);
    }

}
