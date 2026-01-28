package com.expensetracker.app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.PageAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expensetracker.app.domain.CreateExpenseCommand;
import com.expensetracker.app.domain.Expense;
import com.expensetracker.app.domain.ExpensePolicy;
import com.expensetracker.app.domain.ExpenseRepository;
import com.expensetracker.app.persistence.mapper.ExpenseMapper;


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
                                createExpense.getNotes(),
                                LocalDate.now()
                          );

        Expense saved = expenseRepository.save(expense);

        return saved;
    }

    @Transactional(readOnly = true)
    public List<Expense> getExpensesForPeriod(String userId, LocalDate from, LocalDate to) {
        return expenseRepository.findByUserAndDateRange(userId, from, to);
    }

    public Page<Expense> getExpenses(Pageable pageable) {
        return expenseRepository.findAll(pageable);
    }

    public List<Expense> getCursorPageExpenses(LocalDate cursor, int limit) {
        return expenseRepository.findNextExpenses(cursor, limit)
                    .stream()
                    .map(ExpenseMapper::toDomain)
                    .toList();
    }

}
