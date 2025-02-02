package com.example.notification.kafka.listeners;

import com.example.notification.dto.NotificationDto;
import com.example.notification.service.SimpleNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventListener {
    @Autowired
    SimpleNotificationService simpleNotificationService;

    @KafkaListener(topics = "orderProcessed", groupId = "notification")
    public void orderProcessed(NotificationDto notificationDto) {
        simpleNotificationService.sendNotification(notificationDto);
    }
}
