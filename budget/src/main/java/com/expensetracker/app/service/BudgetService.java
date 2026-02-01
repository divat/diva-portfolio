package com.expensetracker.app.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expensetracker.app.api.BudgetRequest;
import com.expensetracker.app.domain.Budget;
import com.expensetracker.app.exception.BudgetNotFoundException;
import com.expensetracker.app.mapper.BudgetMapper;
import com.expensetracker.app.persistence.BudgetEntity;
import com.expensetracker.app.persistence.JpaBudgetRepository;

@Service
@Transactional
public class BudgetService {

    private final JpaBudgetRepository budgetRepository;
    private final BudgetMapper mapper;
    private ApplicationEventPublisher eventPublisher;

    public BudgetService(JpaBudgetRepository budgetRepository, BudgetMapper mapper) {
        this.budgetRepository = budgetRepository;
        this.mapper = mapper;
    }
    
    public Budget create(BudgetRequest request) {

        budgetRepository
            .findByCategoryIdAndMonthAndActiveTrue(
                request.getCategoryId(),
                request.getMonth()
            )
            .ifPresent(b -> {
                throw new IllegalStateException("Budget already exists");
            });

        BudgetEntity entity = new BudgetEntity();
        entity.setCategoryId(request.getCategoryId());
        entity.setMonth(request.getMonth());
        entity.setLimitAmount(request.getLimitAmount());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUserId(request.getUserId());

        return mapper.toDomain(budgetRepository.save(entity));
       // return enrich(mapper.toDomain(budgetRepository.save(entity)));
    }

    public Budget update(Long id, BudgetRequest request) {
        BudgetEntity entity = budgetRepository
            .findByIdAndActiveTrue(id)
            .orElseThrow(() -> new BudgetNotFoundException(id));

        entity.setLimitAmount(request.getLimitAmount());
        entity.setUpdatedAt(LocalDateTime.now());
        return mapper.toDomain(entity);
       // return enrich(mapper.toDomain(entity));
    }

    public Budget get(Long id) {
        return budgetRepository
                    .findByIdAndActiveTrue(id)
                    .map(mapper::toDomain)
                    .orElseThrow(() -> new BudgetNotFoundException(id));

    }

   /* public List<Budget> getByMonth(YearMonth month) {
        return budgetRepository
            .findByMonthAndActiveTrue(month)
            .stream()
            .map(e -> enrich(mapper.toDomain(e)))
            .toList();
    }*/

    public void delete(Long id) {
        BudgetEntity entity = budgetRepository
            .findByIdAndActiveTrue(id)
            .orElseThrow(() -> new BudgetNotFoundException(id));

        entity.setActive(false);
        entity.setUpdatedAt(LocalDateTime.now());
    }

    public List<Budget> getBudgetsByUserIdAndPeriod(String userId, LocalDate period) {
        return budgetRepository.findByUserIdAndMonthAndActiveTrue(userId, period)
                    .stream()
                    .map(mapper::toDomain)
                    .toList();
    }

    /* ---------- Internal ---------- */
   /* private Budget enrich(Budget budget) {

        BigDecimal spent =
            expenseRepository.sumByCategoryAndMonth(
                budget.getCategoryId(),
                budget.getMonth()
            );

        spent = spent == null ? BigDecimal.ZERO : spent;

        budget.setSpentAmount(spent);
        budget.setRemainingAmount(
            budget.getLimitAmount().subtract(spent)
        );
        budget.setStatus(
            BudgetPolicy.calculateStatus(
                budget.getLimitAmount(),
                spent
            )
        );

        eventPublisher.publishEvent(new BudgetStatusEvaluatedEvent(
                budget.getUserId(),
                budget.getCategoryId(),
                budget.getMonth(),
                budget.getStatus().toString(),
                spent,
                budget.getLimitAmount()));

        return budget;
    } 

    public BigDecimal getSpentAmount(Long categoryId, YearMonth month) {
        BigDecimal spent = expenseRepository.sumByCategoryAndMonth(categoryId, month);
        return spent == null ? BigDecimal.ZERO : spent;
    }

    public BigDecimal getLimitAmount(Long categoryId, YearMonth month) {
        return budgetRepository
            .findByCategoryIdAndMonthAndActiveTrue(categoryId, month)
            .map(BudgetEntity::getLimitAmount)
            .orElse(null);
    } */  
}
