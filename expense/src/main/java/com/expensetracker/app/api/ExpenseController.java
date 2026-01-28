package com.expensetracker.app.api;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.app.domain.Expense;
import com.expensetracker.app.persistence.mapper.ExpenseMapper;
import com.expensetracker.app.service.ExpenseService;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @PostMapping
    public ExpenseResponse createExpense(@Valid @RequestBody ExpenseRequest expenseRequest) {
        String userId = "USER_001";

        Expense expense = expenseService.createExpense(ExpenseMapper.toCommand(expenseRequest, userId));
        
        return ExpenseMapper.toResponse(expense);
    }
}
