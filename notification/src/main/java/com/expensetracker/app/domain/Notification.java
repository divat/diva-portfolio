package com.expensetracker.app.domain;

public class Notification {

    private String userId;
    private String type;
    private String message;

    public Notification(String userId, String type, String message) {
        this.userId = userId;
        this.type = type;
        this.message = message;
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

    
}
