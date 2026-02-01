package com.expensetracker.app.api;

public class ApiResponse<T> {

    private boolean success;
    private T data;
    private ApiError error;

    private ApiResponse(boolean success, T data, ApiError error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    // Success response
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null);
    }

    // Failure response
    public static <T> ApiResponse<T> failure(String code, String message) {
        return new ApiResponse<>(false, null, new ApiError(code, message));
    }

    public boolean isSuccess() { return success; }
    public T getData() { return data; }
    public ApiError getError() { return error; }
}

