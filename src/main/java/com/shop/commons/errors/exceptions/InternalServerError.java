package com.shop.commons.errors.exceptions;

import com.shop.commons.errors.ErrorCode;

public class InternalServerError extends BusinessException {

    public InternalServerError() {
        super(ErrorCode.INTERNAL_SERVER_ERROR.getMessage(), ErrorCode.INTERNAL_SERVER_ERROR);
    }

    public InternalServerError(String message) {
        super(message, ErrorCode.INTERNAL_SERVER_ERROR);
    }

    public InternalServerError(String message, Throwable throwable) {
        super(message, ErrorCode.INTERNAL_SERVER_ERROR, throwable);
    }

}
