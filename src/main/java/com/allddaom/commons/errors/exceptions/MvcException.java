package com.allddaom.commons.errors.exceptions;

import com.allddaom.commons.errors.ErrorCode;
import org.springframework.validation.Errors;

public class MvcException extends RuntimeException {

    private final ErrorCode errorCode;
    private Errors errors;

    public MvcException(String message, ErrorCode errorCode, Errors errors) {
        super(message);
        this.errorCode = errorCode;
        this.errors = errors;
    }

    public MvcException(String message, ErrorCode errorCode, Throwable throwable) {
        super(message, throwable);
        this.errorCode = errorCode;
    }

    public MvcException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public MvcException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public MvcException(BusinessException businessException) {
        super(businessException.getMessage());
        this.errorCode = businessException.getErrorCode();
        this.errors = businessException.getErrors();
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public Errors getErrors() {
        return errors;
    }
}
