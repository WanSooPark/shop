package com.shop.commons.errors.exceptions;

import com.shop.commons.errors.ErrorCode;

public class NotAcceptableStatusException extends BusinessException {

    public NotAcceptableStatusException() {
        super(ErrorCode.NOT_ACCEPTABLE_STATUS_EXCEPTION.getMessage(), ErrorCode.NOT_ACCEPTABLE_STATUS_EXCEPTION);
    }

    public NotAcceptableStatusException(String message) {
        super(message, ErrorCode.NOT_ACCEPTABLE_STATUS_EXCEPTION);
    }

}
