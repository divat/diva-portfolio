package com.expensetracker.app.persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.expensetracker.app.domain.Expense;
import com.expensetracker.app.domain.ExpenseRepository;
import com.expensetracker.app.persistence.mapper.ExpenseMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ExpenseRepositoryAdapter implements ExpenseRepository {

    @PersistenceContext
    private EntityManager em;

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

    @Override
    public Page<Expense> findAll(Pageable pageable) {
       return jpaRepository.findAll(pageable)
                .map(ExpenseMapper::toDomain);
    }

    @Override
    public List<ExpenseEntity> findNextExpenses(LocalDate cursor, int limit) {
        return em.createQuery(" SELECT e FROM ExpenseEntity e "
            +"WHERE (e.createdAt < :cursor) "
            +"ORDER BY e.createdAt DESC "
            ,ExpenseEntity.class)
            .setParameter("cursor", cursor)
            .setMaxResults(limit)
            .getResultList();
    }

   
    
}
