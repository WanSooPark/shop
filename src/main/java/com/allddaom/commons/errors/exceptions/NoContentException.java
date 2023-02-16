package com.allddaom.commons.errors.exceptions;

import com.allddaom.commons.errors.ErrorCode;

public class NoContentException extends BusinessException {

    public NoContentException() {
        super(ErrorCode.NO_CONTENT_EXCEPTION.getMessage(), ErrorCode.NO_CONTENT_EXCEPTION);
    }

    public NoContentException(String message) {
        super(message, ErrorCode.NO_CONTENT_EXCEPTION);
    }

}
