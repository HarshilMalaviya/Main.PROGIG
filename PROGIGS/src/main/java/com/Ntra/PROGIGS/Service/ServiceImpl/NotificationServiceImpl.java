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
    public void notifyFreelancer(int contractId) {
        Contract contract = contractRepo.findById(contractId).orElse(null);

        if (contract == null) {
            System.err.println("❌ Contract not found for ID: " + contractId);
            return;
        }

        int freelancerId = contract.getFreelancer().getId();
        System.out.println("📍 Freelancer ID for contract " + contractId + ": " + freelancerId);

        NotificationDto message = new NotificationDto();
        message.setSenderId(contract.getClient().getId());
        message.setSenderName(contract.getClient().getUsername());
        message.setTitle("Contract Closed");
        message.setMessage("Your contract has been closed. Submit Your Review");
        message.setLink("http://localhost:5174/freelancer/contracts");
        message.setTimestamp(LocalDateTime.now().toString());

        CustomWebSocketHandler.sendToFreelancer(freelancerId, message);
    }
}
