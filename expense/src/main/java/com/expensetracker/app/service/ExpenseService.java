package com.expensetracker.app.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expensetracker.app.domain.CreateExpenseCommand;
import com.expensetracker.app.domain.Expense;
import com.expensetracker.app.domain.ExpensePolicy;
import com.expensetracker.app.domain.ExpenseRepository;
import com.expensetracker.app.domain.exception.InvalidExpenseException;
import com.expensetracker.app.event.ExpenseCreatedEvent;
import com.expensetracker.app.persistence.ExpenseEntity;
import com.expensetracker.app.persistence.mapper.ExpenseMapper;


@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpensePolicy expensePolicy;
    private final ApplicationEventPublisher eventPublisher;

    public ExpenseService(ExpenseRepository expenseRepository, ExpensePolicy expensePolicy, 
                        ApplicationEventPublisher eventPublisher){
        this.expenseRepository = expenseRepository;
        this.expensePolicy = expensePolicy;
        this.eventPublisher = eventPublisher;
    }
    

    @Transactional
    public Expense createExpense(CreateExpenseCommand createExpense) {
        
        expensePolicy.validateExpenseCreation(createExpense);

       /* // --- 1️⃣ Real-time budget check ---
        YearMonth month = YearMonth.from(createExpense.getDate());

        BigDecimal spentSoFar = budgetService.getSpentAmount(createExpense.getCatgId(), month);
        BigDecimal budgetLimit = budgetService.getLimitAmount(createExpense.getCatgId(), month);

        if (budgetLimit != null) { // budget exists for this category/month
            BigDecimal newTotal = spentSoFar.add(BigDecimal.valueOf(createExpense.getAmount()));

            if (newTotal.compareTo(budgetLimit) > 0) {
                throw new BudgetExceededException(
                    "Adding this expense exceeds the budget by " +
                    newTotal.subtract(budgetLimit)
                );
            }
        }*/

        Expense expense = Expense.create(createExpense.getUserId(), 
                                createExpense.getCategoryId(),
                                createExpense.getCatgId(),
                                createExpense.getAmount(),
                                createExpense.getDate(),
                                createExpense.getNotes(),
                                LocalDate.now()
                          );

        Expense saved = expenseRepository.save(expense);

        eventPublisher.publishEvent(
            new ExpenseCreatedEvent(
                saved.getId(), 
                saved.getCatgId(), 
                BigDecimal.valueOf(saved.getAmount()), 
                saved.getExpenseDate(),
                saved.getUserId(),
                "EXPENSE_CREATED"
            )
        );

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

    @Transactional
    public Expense updateExpense(Long expenseId, CreateExpenseCommand updateExpense) {
 
        ExpenseEntity entity = expenseRepository.findById(expenseId)
        .orElseThrow(() ->
            new InvalidExpenseException("Expense not found with id :: " + expenseId)
        );

        entity.setAmount(updateExpense.getAmount());
        entity.setCategoryCode(updateExpense.getCategoryId());
        entity.setExpenseDate(updateExpense.getDate());
        entity.setDescription(updateExpense.getNotes());
        entity.setUpdatedAt(LocalDate.now());

        // NO save()
        return ExpenseMapper.toDomain(entity);

    }

    @Transactional
    public void deleteExpense(Long expenseId) {
        ExpenseEntity entity = expenseRepository.findById(expenseId)
        .orElseThrow(() ->
            new InvalidExpenseException("Expense not found with id :: " + expenseId)
        );

        expenseRepository.deleteExpense(entity);
    }

}
