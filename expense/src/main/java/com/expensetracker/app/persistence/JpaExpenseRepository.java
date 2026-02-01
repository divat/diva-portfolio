package com.expensetracker.app.persistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

    List<ExpenseEntity> findByUserIdAndExpenseDateBetween(
            String userId,
            LocalDate from,
            LocalDate to
    );

    @Query("SELECT COALESCE(SUM(e.amount), 0) "
        + "FROM ExpenseEntity e "
        + "WHERE e.categoryId = :categoryId "
        //+ "AND e.active = true "
        + "AND FUNCTION('YEAR_MONTH', e.expenseDate) = :month")
    BigDecimal sumByCategoryAndMonth(
        Long categoryId,
        YearMonth month
    );
}
