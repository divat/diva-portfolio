package com.expensetracker.app.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

public class Budget {

    private Long id;
    private Long categoryId;
    private LocalDate month;
    private BigDecimal limitAmount;
    private boolean active;

    private BigDecimal spentAmount;
    private BigDecimal remainingAmount;
    private BudgetStatus status;
    private String userId;
    private LocalDateTime updatedAt;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public BigDecimal getSpentAmount() {
        return spentAmount;
    }
    public void setSpentAmount(BigDecimal spentAmount) {
        this.spentAmount = spentAmount;
    }
    public BigDecimal getRemainingAmount() {
        return remainingAmount;
    }
    public void setRemainingAmount(BigDecimal remainingAmount) {
        this.remainingAmount = remainingAmount;
    }
    public BudgetStatus getStatus() {
        return status;
    }
    public void setStatus(BudgetStatus status) {
        this.status = status;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void addToSpentAmount(BigDecimal amount) {
        this.spentAmount = this.spentAmount.add(amount);
        this.updatedAt = LocalDateTime.now();
    }

    public void evaluateStatus() {
        BigDecimal ratio = spentAmount.divide(limitAmount, 2, RoundingMode.HALF_UP);

        if (ratio.compareTo(new BigDecimal("1.0")) >= 0) {
            this.status = BudgetStatus.EXCEEDED;
        } else if (ratio.compareTo(new BigDecimal("0.8")) >= 0) {
            this.status = BudgetStatus.WARNING;
        } else {
            this.status = BudgetStatus.OK;
        }
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    
}
