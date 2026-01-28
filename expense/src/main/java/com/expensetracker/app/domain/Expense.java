package com.expensetracker.app.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Expense {

    private final String id;
    private final String userId;
    private final String categoryId;
    private final double amount;
    private final LocalDate expenseDate;
    private final String notes;

    // Private constructor → controlled creation
    public Expense(
            String id,
            String userId,
            String categoryId,
            double amount,
            LocalDate expenseDate,
            String notes
    ) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.expenseDate = expenseDate;
        this.notes = notes;
    }

    // Single factory method
    public static Expense create(
            String userId,
            String categoryId,
            double amount,
            LocalDate expenseDate,
            String notes
    ) {
        return new Expense(
                UUID.randomUUID().toString(),
                userId,
                categoryId,
                amount,
                expenseDate,
                notes
        );
    }

    // Getters only
    public String getId() { return id; }
    public String getUserId() { return userId; }
    public String getCategoryId() { return categoryId; }
    public double getAmount() { return amount; }
    public LocalDate getExpenseDate() { return expenseDate; }
    public String getNotes() { return notes; }
}
