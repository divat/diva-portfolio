package com.expensetracker.app.api;

import java.time.LocalDateTime;

public class ApiError {
    private String code;
    private String message;

    public ApiError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() { return code; }
    public String getMessage() { return message; }
}
