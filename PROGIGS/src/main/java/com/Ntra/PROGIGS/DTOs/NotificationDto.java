package com.Ntra.PROGIGS.DTOs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationDto {
    private int senderId;
    private String senderName;
    private String title;
    private String message;
    private String Link;
    private String timestamp;
}
