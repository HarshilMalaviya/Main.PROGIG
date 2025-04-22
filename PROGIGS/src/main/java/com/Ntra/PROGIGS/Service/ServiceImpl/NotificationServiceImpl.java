package com.Ntra.PROGIGS.Service.ServiceImpl;

import com.Ntra.PROGIGS.DTOs.NotificationDto;
import com.Ntra.PROGIGS.Entity.Contract;
import com.Ntra.PROGIGS.Entity.Notification;
import com.Ntra.PROGIGS.Exception.CustomWebSocketHandler;
import com.Ntra.PROGIGS.Mapper.NotificationMapper;
import com.Ntra.PROGIGS.Repository.ContractRepo;
import com.Ntra.PROGIGS.Repository.NotificationRepo;
import com.Ntra.PROGIGS.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private ContractRepo contractRepo;


    @Autowired
    private NotificationRepo notificationRepository;

    @Autowired
    private NotificationMapper notificationMapper;
    @Override
    public void notifyFreelancer(Integer contractId) {
        Contract contract = contractRepo.findById(contractId).orElse(null);
        if (contract == null) return;

        Integer freelancerId = contract.getFreelancer().getId();


        NotificationDto message = new NotificationDto();
        message.setSenderName(contract.getClient().getUsername());
        message.setTitle("Contract Closed");
        message.setMessage("Your contract has been closed. Submit your review.");
        message.setLink("http://localhost:5173/freelancer/review/" + contractId);
        message.setTimestamp(String.valueOf(LocalDateTime.now()));

        // Save to database
        Notification notif = new Notification();
        notif.setSender(contract.getClient());
        notif.setReceiver(contract.getFreelancer());
        notif.setTitle(message.getTitle());
        notif.setMessage(message.getMessage());
        notif.setLink(message.getLink());
        notif.setTimestamp(message.getTimestamp());
        notificationRepository.save(notif);


        CustomWebSocketHandler.sendToFreelancer(freelancerId, message);
    }
    @Override
    public List<NotificationDto> getNotificationsForUser(Integer userId) {
        List<Notification> notifications = notificationRepository.findByReceiverIdOrderByTimestampDesc(userId);
        return notifications.stream().map(notificationMapper::mapToDto).toList();
    }
    @Override
    public void deleteNotification(Integer id) {
        notificationRepository.deleteById(id);
    }
    @Override
    public void deleteAllForUser(Integer userId) {
        List<Notification> notifications = notificationRepository.findByReceiverIdOrderByTimestampDesc(userId);
        notificationRepository.deleteAll(notifications);
    }
}
