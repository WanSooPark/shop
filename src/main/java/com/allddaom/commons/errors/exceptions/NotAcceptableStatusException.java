package com.allddaom.commons.errors.exceptions;

import com.allddaom.commons.errors.ErrorCode;

public class NotAcceptableStatusException extends BusinessException {

    public NotAcceptableStatusException() {
        super(ErrorCode.NOT_ACCEPTABLE_STATUS_EXCEPTION.getMessage(), ErrorCode.NOT_ACCEPTABLE_STATUS_EXCEPTION);
    }

    public NotAcceptableStatusException(String message) {
        super(message, ErrorCode.NOT_ACCEPTABLE_STATUS_EXCEPTION);
    }

}
