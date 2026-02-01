package com.expensetracker.app.domain;

public enum BudgetStatus {

    OK,        // Spending is within safe limit
    WARNING,   // Spending crossed warning threshold (ex: 80%)
    EXCEEDED   // Budget limit exceeded
}
