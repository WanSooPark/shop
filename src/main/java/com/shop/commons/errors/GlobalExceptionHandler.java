package com.shop.commons.errors;

import com.shop.commons.errors.exceptions.BusinessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException e) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        final ErrorCode errorCode = e.getErrorCode();
        final Errors errors = e.getErrors();
        final ErrorResponse response = ErrorResponse.of(errorCode, e.getMessage(), errors);
        return ResponseEntity.status(HttpStatus.valueOf(errorCode.getStatus()))
                .headers(httpHeaders)
                .body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleBadRequestException(final MethodArgumentNotValidException e) {
        final Errors errors = e.getBindingResult();

        return getBadRequestErrorResponseEntity(errors);
    }

    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ErrorResponse> handleBadRequestException(final BindException e) {
        final Errors errors = e.getBindingResult();

        return getBadRequestErrorResponseEntity(errors);
    }

    private ResponseEntity<ErrorResponse> getBadRequestErrorResponseEntity(Errors errors) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        final ErrorCode errorCode = ErrorCode.BAD_REQUEST_EXCEPTION;

        StringBuilder message = new StringBuilder();
        message.append("Validation failed for Object '")
                .append(errors.getObjectName())
                .append("' Bad Request");

        final ErrorResponse response = ErrorResponse.of(errorCode, message.toString(), errors);
        return ResponseEntity.status(HttpStatus.valueOf(errorCode.getStatus()))
                .headers(httpHeaders)
                .body(response);
    }

}
