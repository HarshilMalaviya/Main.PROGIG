package com.Ntra.PROGIGS.Service.ServiceImpl;

import com.Ntra.PROGIGS.DTOs.NotificationDto;
import com.Ntra.PROGIGS.Entity.Contract;
import com.Ntra.PROGIGS.Exception.CustomWebSocketHandler;
import com.Ntra.PROGIGS.Repository.ContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationServiceImpl {
    @Autowired
    private ContractRepo contractRepo;
    public void notifyFreelancer(Integer contractId) {
        Contract contract = contractRepo.findById(contractId).orElse(null);
        Integer freelancerId = contract.getFreelancer().getId();
        NotificationDto message = new NotificationDto();
        message.setSenderId(contract.getClient().getId());
        message.setSenderName(contract.getClient().getUsername());
        message.setTitle("Contract Closed");
        message.setMessage("Your contract has been closed. Submit Your Review");
        message.setLink("http://localhost:5173/employer/contracts");
        message.setTimestamp(LocalDateTime.now());
        CustomWebSocketHandler.sendToFreelancer(freelancerId, message);
    }
}
