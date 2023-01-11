package com.shop.commons.errors.exceptions;

import com.shop.commons.errors.ErrorCode;

public class AccessDeniedException extends BusinessException {

    public AccessDeniedException() {
        super(ErrorCode.ILLEGAL_ARGUMENT_EXCEPTION.getMessage(), ErrorCode.ACCESS_DENIED_EXCEPTION);
    }

    public AccessDeniedException(String message) {
        super(message, ErrorCode.ACCESS_DENIED_EXCEPTION);
    }

}
