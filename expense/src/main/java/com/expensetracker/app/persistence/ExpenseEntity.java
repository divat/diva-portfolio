package com.expensetracker.app.persistence;

import java.math.BigDecimal;
import java.time.LocalDate;

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

    // JPA requires this
    protected ExpenseEntity() {
    }

    // Used by mapper
    public ExpenseEntity(
            String expenseId,
            String userId,
            double amount,
            String categoryCode,
            LocalDate expenseDate,
            String description
    ) {
        this.expenseId = expenseId;
        this.userId = userId;
        this.amount = amount;
        this.categoryCode = categoryCode;
        this.expenseDate = expenseDate;
        this.description = description;
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
}
