package com.lynhatkhanh.identity_service.exception;

public enum ErrorCode {
    UNCATEGORIZED_ERROR(9999, "Uncategorized error!"),
    INVALID_ENUM_KEY(1001, "Uncategorized error!"),
    USER_EXISTED(1002, "User existed!"),
    USER_NOT_EXISTED(1003, "User not existed!"),
    INVALID_USERNAME(1004, "Username must be at least 3 characters!"),
    INVALID_PASSWORD(1005, "Password must be at least 8 characters!"),
    UNAUTHENTICATED(1005, "Unauthenticated!"),
    USER_NOT_FOUND(2001, "User not found!")

    ;

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
