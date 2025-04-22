package com.Ntra.PROGIGS.DTOs;

import lombok.Data;

@Data
public class NotificationDto {
    private int id;
    private String senderName;
    private String title;
    private String message;
    private String Link;
    private String timestamp;
}
