package com.expensetracker.app.listeners;

import java.time.LocalDateTime;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.expensetracker.app.persistence.JpaNotificationRepository;
import com.expensetracker.app.persistence.NotificationEntity;

import jakarta.transaction.Transactional;

@Component
public class BudgetNotificationListener {

    private final JpaNotificationRepository notificationRepository;

    public BudgetNotificationListener(JpaNotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Transactional
    @EventListener
    public void onBudgetThresholdCrossed(com.expensetracker.app.event.BudgetThresholdCrossedEvent event) {

        NotificationEntity notification = new NotificationEntity();
        notification.setType("BUDGET_" + event.getStatus());
        //notification.setReferenceId(event.getBudgetId());
        notification.setMessage(
            "Budget " + event.getStatus()
            + " for category " + event.getCategoryId()
            + " in " + event.getMonth()
        );
        notification.setCreatedAt(LocalDateTime.now());

        notificationRepository.save(notification);
    }
}

