package com.expensetracker.app.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expensetracker.app.persistence.NotificationEntity;
import com.expensetracker.app.service.NotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<NotificationEntity>> getUserNotifications(
            @RequestParam(name = "userId") String userId) {

        return new ResponseEntity<List<NotificationEntity>>(service.getUserNotifications(userId), HttpStatus.FOUND);
    }
}
