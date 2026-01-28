package com.expensetracker.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expensetracker.app.domain.CreateExpenseCommand;
import com.expensetracker.app.domain.Expense;
import com.expensetracker.app.domain.ExpensePolicy;
import com.expensetracker.app.domain.ExpenseRepository;


@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpensePolicy expensePolicy;

    public ExpenseService(ExpenseRepository expenseRepository, ExpensePolicy expensePolicy) {
        this.expenseRepository = expenseRepository;
        this.expensePolicy = expensePolicy;
    }

    @Transactional
    public Expense createExpense(CreateExpenseCommand createExpense) {
        
        expensePolicy.validateExpenseCreation(createExpense);

        Expense expense = Expense.create(createExpense.getUserId(), 
                                createExpense.getCategoryId(),
                                createExpense.getAmount(),
                                createExpense.getDate(),
                                createExpense.getNotes()
                          );
        
        Expense saved = expenseRepository.save(expense);

        return saved;
    }

    @Transactional(readOnly = true)
    public List<Expense> getExpensesForPeriod(String userId, LocalDate from, LocalDate to) {
        return expenseRepository.findByUserAndDateRange(userId, from, to);
    }

}
