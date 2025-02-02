package com.example.notification.service;

import com.example.notification.dto.NotificationDto;
import com.example.notification.interfaces.NotificationInterface;
import com.example.notification.model.Notification;
import com.example.notification.repository.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleNotificationService implements NotificationInterface {
    @Autowired
    NotificationRepo notificationRepo;
    @Override
    public void sendNotification(NotificationDto notificationDto) {
        Notification notification = new Notification();
        notification.setUserId(notificationDto.getUserId());
        notification.setMessage(notificationDto.getMessage());
        notificationRepo.save(notification);
        System.out.println(notificationDto.getMessage());
    }
}
