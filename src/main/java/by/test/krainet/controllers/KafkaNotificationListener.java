package by.test.krainet.controllers;


import by.test.krainet.dto.Letter;
import by.test.krainet.service.AdminService;
import by.test.krainet.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaNotificationListener {

    private final NotificationService notificationService;
    private final AdminService adminService;

    @Autowired
    public KafkaNotificationListener(NotificationService notificationService, AdminService adminService) {
        this.notificationService = notificationService;
        this.adminService = adminService;
    }

    @KafkaListener(topics = "notification", groupId = "notification-group")
    public void handleUserEvent(Letter event) {
        List<String> adminEmails = adminService.getAdminEmails();

        String subject = String.format("%s пользователь %s",
                getActionText(event.getEventType()),
                event.getUsername()
        );

        String text = String.format("%s пользователь с именем - %s, паролем - %s и почтой - %s.",
                getActionText(event.getEventType()),
                event.getUsername(),
                event.getPassword(),
                event.getEmail()
        );

        adminEmails.forEach(email ->
                notificationService.sendSimpleMessage(email, subject, text)
        );
    }

    private String getActionText(String eventType) {
        return switch (eventType) {
            case "CREATED" -> "Создан";
            case "UPDATED" -> "Изменен";
            case "DELETED" -> "Удален";
            default -> "Ошибка статуса";
        };
    }
}