package com.example.payment.service;

import com.example.payment.dto.NotificationDto;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {
    public NotificationDto sendNotification(String userId, String message) {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setUserId(userId);
        notificationDto.setMessage(message);
        return notificationDto;
    }
}
