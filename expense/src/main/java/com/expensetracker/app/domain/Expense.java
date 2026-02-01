package com.expensetracker.app.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Expense {

    private String id;
    private String userId;
    private Long catgId;
    private double amount;
    private LocalDate expenseDate;
    private String notes;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String categoryId;

    // Private constructor → controlled creation
    public Expense(
            String id,
            String userId,
            String categoryId,
            Long categoryId2,
            double amount,
            LocalDate expenseDate,
            String notes,
            LocalDate createdAt
    ) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.catgId = categoryId2;
        this.amount = amount;
        this.expenseDate = expenseDate;
        this.notes = notes;
        this.createdAt = createdAt;
    }

    // Single factory method
    public static Expense create(
            String userId,
            String categoryCode,
            Long categoryId,
            double amount,
            LocalDate expenseDate,
            String notes,
            LocalDate createdAt
    ) {
        return new Expense(
                UUID.randomUUID().toString(),
                userId,
                categoryCode,
                categoryId,
                amount,
                expenseDate,
                notes,
                createdAt
        );
    }


    // Getters only
    public String getId() { return id; }
    public String getUserId() { return userId; }
    public Long getCatgId() { return catgId; }
    public double getAmount() { return amount; }
    public LocalDate getExpenseDate() { return expenseDate; }
    public String getNotes() { return notes; }
    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public String getCategoryCode() {
        return categoryId;
    }

    public void setCategoryCode(String categoryId) {
        this.categoryId = categoryId;
    }

    
    
}
