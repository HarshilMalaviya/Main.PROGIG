package com.Ntra.PROGIGS.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class NotificationMessageDTO {
    private String message;
    private String reviewLink; // Link to review page
    private LocalDateTime timestamp;




}

