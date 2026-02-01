package com.expensetracker.app.listeners;

import java.time.LocalDate;
import java.time.YearMonth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.expensetracker.app.domain.BudgetStatus;
import com.expensetracker.app.event.BudgetThresholdCrossedEvent;
import com.expensetracker.app.event.ExpenseCreatedEvent;
import com.expensetracker.app.persistence.JpaBudgetRepository;

import jakarta.transaction.Transactional;

@Component
public class BudgetExpenseListener {

    private final JpaBudgetRepository budgetRepository;
    private final ApplicationEventPublisher eventPublisher;

    public BudgetExpenseListener(JpaBudgetRepository budgetRepository, ApplicationEventPublisher eventPublisher) {
        this.budgetRepository = budgetRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    @EventListener
    public void handleExpenseCreated(ExpenseCreatedEvent event) {

        YearMonth month = YearMonth.from(event.getExpenseDate());
        LocalDate period = month.atDay(1);

        budgetRepository
            .findByCategoryIdAndMonthAndActiveTrue(event.getCategoryId(), period)
            .ifPresent(budget -> {

                budget.addToSpentAmount(event.getAmount());
                BudgetStatus previous = budget.getStatus();
                budget.evaluateStatus();

                budgetRepository.save(budget);

                if (budget.getStatus() != previous &&
                   (budget.getStatus() == BudgetStatus.WARNING ||
                    budget.getStatus() == BudgetStatus.EXCEEDED)) {

                    eventPublisher.publishEvent(
                        new BudgetThresholdCrossedEvent(
                            budget.getId(),
                            budget.getCategoryId(),
                            budget.getMonth(),
                            budget.getStatus()
                        )
                    );
                }
            });
    }


}
