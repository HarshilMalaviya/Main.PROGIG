package com.Ntra.PROGIGS.Controller;

import com.Ntra.PROGIGS.DTOs.NotificationDto;
import com.Ntra.PROGIGS.Service.NotificationService;
import com.Ntra.PROGIGS.Service.ServiceImpl.NotificationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {
    @Autowired
    private final NotificationService notificationService;

    @GetMapping("/{userId}")
    public List<NotificationDto> getUserNotifications(@PathVariable Integer userId) {
        return notificationService.getNotificationsForUser(userId);
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<?> deleteOne(@PathVariable Integer notificationId) {
        notificationService.deleteNotification(notificationId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteAll(@PathVariable Integer userId) {
        notificationService.deleteAllForUser(userId);
        return ResponseEntity.ok().build();
    }
}
