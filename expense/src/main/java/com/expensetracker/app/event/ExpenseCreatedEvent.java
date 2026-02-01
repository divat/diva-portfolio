package com.expensetracker.app.event;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

public class ExpenseCreatedEvent {

    private String expenseId;
    private String userId;
    private Long categoryId;
    private BigDecimal amount;
    private YearMonth month;
    private String message;
    private LocalDate expenseDate;

    public ExpenseCreatedEvent(
            String expenseId,
            Long categoryId,
            BigDecimal amount,
            LocalDate expenseDate,
            String userId,
        String message) {
        this.expenseId = expenseId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.expenseDate = expenseDate;
        this.userId = userId;
        this.message = message;
    }

    public String getExpenseId() {
        return expenseId;
    }
    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public YearMonth getMonth() {
        return month;
    }
    public void setMonth(YearMonth month) {
        this.month = month;
    }



    public String getMessage() {
        return message;
    }



    public void setMessage(String message) {
        this.message = message;
    }



    public LocalDate getExpenseDate() {
        return expenseDate;
    }



    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }

    
}
