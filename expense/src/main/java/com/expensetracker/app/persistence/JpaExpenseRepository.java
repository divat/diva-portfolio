package com.expensetracker.app.persistence;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

    List<ExpenseEntity> findByUserIdAndExpenseDateBetween(
            String userId,
            LocalDate from,
            LocalDate to
    );
}
