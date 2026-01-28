package com.expensetracker.app.persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.expensetracker.app.domain.Expense;
import com.expensetracker.app.domain.ExpenseRepository;
import com.expensetracker.app.persistence.mapper.ExpenseMapper;

@Repository
public class ExpenseRepositoryAdapter implements ExpenseRepository {

    private final JpaExpenseRepository jpaRepository;

    public ExpenseRepositoryAdapter(JpaExpenseRepository jpaExpenseRepository) {
        this.jpaRepository = jpaExpenseRepository;
    }

    @Override
    public Expense save(Expense expense) {
        ExpenseEntity entity = ExpenseMapper.toEntity(expense);
        ExpenseEntity saved = jpaRepository.save(entity);
        return ExpenseMapper.toDomain(saved);
    }

    @Override
    public Optional<Expense> findById(Long expenseId) {
        return jpaRepository.findById(expenseId)
                .map(ExpenseMapper::toDomain);
    }

    @Override
    public List<Expense> findByUserAndDateRange(String userid, LocalDate from, LocalDate to) {
        return jpaRepository.findByUserIdAndExpenseDateBetween(userid, from, to)
                    .stream()
                    .map(ExpenseMapper::toDomain)
                    .toList();
    }

    
}
