package com.expensetracker.app.persistence;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

import com.expensetracker.app.domain.BudgetStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "budgets",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"category_id", "month"}
    )
)
public class BudgetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "budget_month", nullable = false)
    private LocalDate month;

    @Column(name = "limit_amount", nullable = false)
    private BigDecimal limitAmount;

     @Column(name = "spent_amount")
    private BigDecimal spentAmount = new BigDecimal(0);

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private BudgetStatus status;

    private String userId;

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
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    public BigDecimal getSpentAmount() {
        return spentAmount;
    }
    public void setSpentAmount(BigDecimal spentAmount) {
        this.spentAmount = spentAmount;
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
    
}

