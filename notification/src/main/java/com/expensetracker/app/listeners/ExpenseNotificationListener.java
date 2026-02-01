package com.expensetracker.app.listeners;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.expensetracker.app.domain.Notification;
import com.expensetracker.app.service.NotificationService;

@Component
public class ExpenseNotificationListener {

    private final NotificationService notificationService;

    public ExpenseNotificationListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Async
    @EventListener
    public void handle(com.expensetracker.app.event.ExpenseCreatedEvent event) {

        notificationService.create(
            new Notification(
                event.getUserId(),
                event.getMessage(),
                "Expense of ₹" + event.getAmount() + " added"
            )
        );
    }
}

