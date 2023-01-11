package com.shop.commons.errors.exceptions;

import com.shop.commons.errors.ErrorCode;

public class IllegalArgumentException extends BusinessException {

    public IllegalArgumentException() {
        super(ErrorCode.ILLEGAL_ARGUMENT_EXCEPTION.getMessage(), ErrorCode.ILLEGAL_ARGUMENT_EXCEPTION);
    }

    public IllegalArgumentException(String message) {
        super(message, ErrorCode.ILLEGAL_ARGUMENT_EXCEPTION);
    }

}
