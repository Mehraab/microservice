package com.example.notification.kafka.listeners;

import com.example.notification.dto.NotificationDto;
import com.example.notification.service.SimpleNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class InventoryEventListener {
    @Autowired
    SimpleNotificationService simpleNotificationService;
    @KafkaListener(topics = "orderNotFound", groupId = "notification")
    public void orderNotFound(NotificationDto notificationDto) {
        simpleNotificationService.sendNotification(notificationDto);
    }
    @KafkaListener(topics = "orderConfirmed", groupId = "notification")
    public void orderConfirmed(NotificationDto notificationDto) {
        simpleNotificationService.sendNotification(notificationDto);
    }
    @KafkaListener(topics = "outOfStock", groupId = "notification")
    public void outOfStock(NotificationDto notificationDto) {
        simpleNotificationService.sendNotification(notificationDto);
    }
}
