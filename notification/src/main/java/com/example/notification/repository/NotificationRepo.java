package com.example.notification.repository;

import com.example.notification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, String> {
    Optional<Notification> findByNotificationId(String notificationId);
    List<Notification> findByUserId(String userId);
}
