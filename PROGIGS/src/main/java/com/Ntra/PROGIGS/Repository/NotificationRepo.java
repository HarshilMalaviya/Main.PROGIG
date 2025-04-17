package com.Ntra.PROGIGS.Repository;

import com.Ntra.PROGIGS.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface NotificationRepo extends JpaRepository<Notification, Integer> {

    List<Notification> findByReceiverIdOrderByTimestampDesc(Integer userId);
}
