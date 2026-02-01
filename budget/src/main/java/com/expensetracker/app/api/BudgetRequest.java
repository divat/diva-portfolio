package com.expensetracker.app.api;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;

public class BudgetRequest {
    private Long categoryId;
    private LocalDate month;
    private BigDecimal limitAmount;
    private String userId;
    
    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public LocalDate getMonth() {
        return month;
    }
    public void setMonth(LocalDate month) {
        this.month = month;
    }
    public BigDecimal getLimitAmount() {
        return limitAmount;
    }
    public void setLimitAmount(BigDecimal limitAmount) {
        this.limitAmount = limitAmount;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    
}

