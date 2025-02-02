package com.example.notification.kafka.listeners;

import com.example.notification.dto.NotificationDto;
import com.example.notification.service.SimpleNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventListener {
    @Autowired
    SimpleNotificationService simpleNotificationService;
    @KafkaListener(topics = "invalidRequest", groupId = "notification")
    public void invalidRequest(NotificationDto notificationDto) {
        simpleNotificationService.sendNotification(notificationDto);
    }
    @KafkaListener(topics = "insufficientAmount", groupId = "notification")
    public void insufficientAmount(NotificationDto notificationDto) {
        simpleNotificationService.sendNotification(notificationDto);
    }
    @KafkaListener(topics = "paymentSuccess", groupId = "notification")
    public void paymentSuccess(NotificationDto notificationDto) {
        simpleNotificationService.sendNotification(notificationDto);
    }
    @KafkaListener(topics = "missingPaymentMethod", groupId = "notification")
    public void missingPaymentMethod(NotificationDto notificationDto) {
        simpleNotificationService.sendNotification(notificationDto);
    }
    @KafkaListener(topics = "unconfirmedOrder", groupId = "notification")
    public void unconfirmedOrder(NotificationDto notificationDto) {
        simpleNotificationService.sendNotification(notificationDto);
    }
}
