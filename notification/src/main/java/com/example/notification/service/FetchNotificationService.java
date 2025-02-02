package com.example.notification.service;

import com.example.notification.model.Notification;
import com.example.notification.repository.NotificationRepo;
import com.example.notification.utils.ParseClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FetchNotificationService {
    @Autowired
    NotificationRepo notificationRepo;
    @Autowired
    ParseClaims parseClaims;

    public List<Notification> getAll() {
        return notificationRepo.findAll();
    }

    public List<Notification> getForOne(String token) {
        String userId = parseClaims.getClaims(token);
        return notificationRepo.findByUserId(userId);
    }
}
