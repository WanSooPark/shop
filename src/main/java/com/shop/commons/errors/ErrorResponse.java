package com.shop.commons.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+9")
    private final LocalDateTime timestamp = LocalDateTime.now();
    private String message;
    private int status;
    private Errors errors;
    private String code;

    private ErrorResponse(final ErrorCode code, final String message, final Errors errors) {
        this.message = message;
        this.status = code.getStatus();
        this.errors = errors;
        this.code = code.getCode();
    }

    public static ErrorResponse of(final ErrorCode code, final String message, final Errors errors) {
        return new ErrorResponse(code, message, errors);
    }

    public static ErrorResponse of(final ErrorCode code, final Errors errors) {
        return new ErrorResponse(code, code.getMessage(), errors);
    }

    public static ErrorResponse of(final ErrorCode code, final String message) {
        return new ErrorResponse(code, message, null);
    }

    public static ErrorResponse of(final ErrorCode code) {
        return new ErrorResponse(code, code.getMessage(), null);
    }

}
