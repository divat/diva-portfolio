package com.expensetracker.app.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository {

    Expense save(Expense expense);

    Optional<Expense> findById(Long expenseId);

    List<Expense> findByUserAndDateRange(
        String userid,
        LocalDate from,
        LocalDate to
    );
}
