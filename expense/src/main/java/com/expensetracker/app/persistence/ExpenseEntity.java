package com.expensetracker.app.persistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

@Entity
@Table(
    name = "expenses",
    indexes = {
        @Index(name = "idx_expense_user", columnList = "user_id"),
        @Index(name = "idx_expense_date", columnList = "expense_date")
    }
)
@SQLDelete(sql = "UPDATE expenses SET deleted = true, deleted_at = now() WHERE id = ?")
@Where(clause = "deleted = false")
public class ExpenseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "expense_id", nullable = false, unique = true)
    private String expenseId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(nullable = false)
    private double amount;

    @Column(name = "category_code", nullable = false)
    private String categoryCode;

    @Column(name = "expense_date", nullable = false)
    private LocalDate expenseDate;

    @Column(length = 255)
    private String description;

    @Column(name = "created_at")
    private LocalDate createdAt;

    
    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    private boolean deleted;

    @Column(name = "category_id")
    private Long categoryId;

    // JPA requires this
    protected ExpenseEntity() {
    }

    // Used by mapper
    public ExpenseEntity(
            String expenseId,
            String userId,
            double amount,
            String categoryCode,
            Long categoryId,
            LocalDate expenseDate,
            String description,
            LocalDate createdAt
    ) {
        this.expenseId = expenseId;
        this.userId = userId;
        this.amount = amount;
        this.categoryCode = categoryCode;
        this.categoryId = categoryId;
        this.expenseDate = expenseDate;
        this.description = description;
        this.createdAt = createdAt;
    }

    // Getters only (no business logic)

    public Long getId() {
        return id;
    }

    public String getExpenseId() {
        return expenseId;
    }

    public String getUserId() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    
    
}
