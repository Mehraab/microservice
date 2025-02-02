package com.example.order.dto;

public class NotificationDto {
    private String userId;
    private String message;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }
}
