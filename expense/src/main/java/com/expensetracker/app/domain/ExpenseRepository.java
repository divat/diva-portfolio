package com.expensetracker.app.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.expensetracker.app.persistence.ExpenseEntity;

public interface ExpenseRepository {

    Expense save(Expense expense);

    Optional<Expense> fecthExpense(Long expenseId);

    List<Expense> findByUserAndDateRange(
        String userid,
        LocalDate from,
        LocalDate to
    );

    Page<Expense> findAll(Pageable pageable);

    List<ExpenseEntity> findNextExpenses(LocalDate cursor, int limit);

    Optional<ExpenseEntity> findById(Long expenseId);

    void deleteExpense(ExpenseEntity entity);
 
}
