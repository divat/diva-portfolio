package com.expensetracker.app.mapper;

import org.springframework.stereotype.Component;

import com.expensetracker.app.domain.Budget;
import com.expensetracker.app.persistence.BudgetEntity;

@Component
public class BudgetMapper {

    public Budget toDomain(BudgetEntity entity) {
        Budget budget = new Budget();
        budget.setId(entity.getId());
        budget.setCategoryId(entity.getCategoryId());
        budget.setMonth(entity.getMonth());
        budget.setLimitAmount(entity.getLimitAmount());
        budget.setActive(entity.isActive());
        return budget;
    }

    public BudgetEntity toEntity(Budget domain) {
        BudgetEntity entity = new BudgetEntity();
        entity.setId(domain.getId());
        entity.setCategoryId(domain.getCategoryId());
        entity.setMonth(domain.getMonth());
        entity.setLimitAmount(domain.getLimitAmount());
        entity.setActive(domain.isActive());
        return entity;
    }
}

