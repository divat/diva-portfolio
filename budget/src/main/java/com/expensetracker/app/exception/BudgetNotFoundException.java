package com.expensetracker.app.exception;

public class BudgetNotFoundException
        extends RuntimeException {

    public BudgetNotFoundException(Long id) {
        super("Budget not found :: " + id);
    }
}
