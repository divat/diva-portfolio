package com.expensetracker.app.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaNotificationRepository
        extends JpaRepository<NotificationEntity, Long> {

    List<NotificationEntity> findByUserIdOrderByCreatedAtDesc(String userId);
}

