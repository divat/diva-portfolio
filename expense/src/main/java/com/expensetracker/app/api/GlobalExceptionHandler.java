package com.expensetracker.app.api;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.expensetracker.app.domain.exception.InvalidExpenseException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Business exception
    @ExceptionHandler(InvalidExpenseException.class)
    public ApiResponse<Void> handleInvalidExpense(
            InvalidExpenseException ex,
            HttpServletRequest request
    ) {
        log.warn("Invalid expense: {}", ex.getMessage());

        return ApiResponse.failure("INVALID_EXPENSE", ex.getMessage());

        
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleAll(
            Exception ex,
            HttpServletRequest request
    ) {
        log.error("Unexpected error", ex);

        return ApiResponse.failure(
                "INTERNAL_ERROR",
                "Something went wrong"
        );
    }
}
