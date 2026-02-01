package com.expensetracker.app.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expensetracker.app.domain.Notification;
import com.expensetracker.app.persistence.JpaNotificationRepository;
import com.expensetracker.app.persistence.NotificationEntity;


@Service
@Transactional
public class NotificationService {

    private final JpaNotificationRepository repository;

    public NotificationService(JpaNotificationRepository repository) {
        this.repository = repository;
    }

    public void create(Notification notification) {
        NotificationEntity entity =
                new NotificationEntity(
                        notification.getUserId(),
                        notification.getType(),
                        notification.getMessage()
                );
        repository.save(entity);
    }

    @Transactional(readOnly = true)
    public List<NotificationEntity> getUserNotifications(String userId) {
        return repository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public void markAsRead(Long notificationId) {
        NotificationEntity entity = repository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        entity.markAsRead();
    }
}

