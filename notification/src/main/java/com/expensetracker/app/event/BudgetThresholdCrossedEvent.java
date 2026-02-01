package com.expensetracker.app.event;

import java.time.LocalDate;
import java.time.YearMonth;

import com.expensetracker.app.domain.BudgetStatus;

public class BudgetThresholdCrossedEvent {

    private final Long budgetId;
    private final Long categoryId;
    private final LocalDate month;
    private final BudgetStatus status;
    
    public BudgetThresholdCrossedEvent(
            Long budgetId,
            Long categoryId,
            LocalDate month,
            BudgetStatus status) {
        this.budgetId = budgetId;
        this.categoryId = categoryId;
        this.month = month;
        this.status = status;
    }

    public Long getBudgetId() { return budgetId; }
    public Long getCategoryId() { return categoryId; }
    public LocalDate getMonth() { return month; }
    public BudgetStatus getStatus() { return status; }

    
}
