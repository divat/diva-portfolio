package com.expensetracker.app.persistence.mapper;

import com.expensetracker.app.api.ExpenseRequest;
import com.expensetracker.app.api.ExpenseResponse;
import com.expensetracker.app.domain.CreateExpenseCommand;
import com.expensetracker.app.domain.Expense;
import com.expensetracker.app.persistence.ExpenseEntity;

public final class ExpenseMapper {

    private ExpenseMapper() {

    }

    public static ExpenseEntity toEntity(Expense expense) {
        return new ExpenseEntity(
                expense.getId(),
                expense.getUserId(),
                expense.getAmount(),
                expense.getCategoryId(),
                expense.getExpenseDate(),
                expense.getNotes()
        );
    }

    // ENTITY -> DOMAIN (load from DB)
    public static Expense toDomain(ExpenseEntity entity) {
        return new Expense(
                entity.getExpenseId(),
                entity.getUserId(),
                entity.getCategoryCode(),
                entity.getAmount(),
                entity.getExpenseDate(),
                entity.getDescription()
        );
    }

    public static CreateExpenseCommand toCommand(ExpenseRequest request, String userId) {
        return new CreateExpenseCommand(userId, request.getCategoryId(), request.getAmount(), 
                    request.getExpenseDate(), request.getNotes());
    }

    public static ExpenseResponse toResponse(Expense expense) {
        ExpenseResponse response = new ExpenseResponse();
        response.setExpenseId(expense.getId());
        response.setCategoryId(expense.getCategoryId());
        response.setAmount(expense.getAmount());
        response.setExpenseDate(expense.getExpenseDate());
        response.setNotes(expense.getNotes());
        return response;
    }
}
