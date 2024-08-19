package com.lynhatkhanh.identity_service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_ERROR(9999, "Uncategorized error!", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_ENUM_KEY(1001, "Uncategorized error!", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed!", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1003, "User not existed!", HttpStatus.BAD_REQUEST),
    INVALID_USERNAME(1004, "Username must be at least 3 characters!", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1005, "Password must be at least 8 characters!", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1005, "Unauthenticated!", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1006, "You do not have permission!", HttpStatus.FORBIDDEN),
    USER_NOT_FOUND(2001, "User not found!", HttpStatus.NOT_FOUND),
    ROLE_NOT_FOUND(2002, "Role not found!", HttpStatus.NOT_FOUND)

    ;

    private int code;
    private String message;
    private HttpStatusCode httpStatusCode;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

}
