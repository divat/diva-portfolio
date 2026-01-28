package com.expensetracker.app.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.expensetracker.app.persistence.ExpenseEntity;

public interface ExpenseRepository {

    Expense save(Expense expense);

    Optional<Expense> findById(Long expenseId);

    List<Expense> findByUserAndDateRange(
        String userid,
        LocalDate from,
        LocalDate to
    );

    Page<Expense> findAll(Pageable pageable);

    List<ExpenseEntity> findNextExpenses(LocalDate cursor, int limit);
}
