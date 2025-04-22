package com.Ntra.PROGIGS.Service;

import com.Ntra.PROGIGS.DTOs.NotificationDto;

import java.util.List;

public interface NotificationService {

    void notifyFreelancer(Integer contractId);

    List<NotificationDto> getNotificationsForUser(Integer userId);

    void deleteNotification(Integer id);

    void deleteAllForUser(Integer userId);
}
