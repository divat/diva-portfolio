package com.expensetracker.app.persistence;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notifications")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;

    private String type;   // EXPENSE_CREATED, BUDGET_WARNING, BUDGET_EXCEEDED

    @Column(length = 500)
    private String message;

    private boolean read;

    private LocalDateTime createdAt;

    public NotificationEntity() {}

    public NotificationEntity(String userId, String type, String message) {
        this.userId = userId;
        this.type = type;
        this.message = message;
        this.read = false;
        this.createdAt = LocalDateTime.now();
    }


    // getters + markAsRead()
    public void markAsRead() {
        this.read = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    
}

