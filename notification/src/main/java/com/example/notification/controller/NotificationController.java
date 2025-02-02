package com.example.notification.controller;

import com.example.notification.model.Notification;
import com.example.notification.service.FetchNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    @Autowired
    FetchNotificationService fetchNotificationService;

    @GetMapping("/getAll")
    public List<Notification> getAllNotification() {
        return fetchNotificationService.getAll();
    }
    @GetMapping("/get-for-one-user")
    public List<Notification> getAllNotification(@RequestHeader("Authorization") String token) {
        return fetchNotificationService.getForOne(token);
    }
}
