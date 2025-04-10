package com.Ntra.PROGIGS.Service.ServiceImpl;

import com.Ntra.PROGIGS.Entity.Contract;
import com.Ntra.PROGIGS.Exception.CustomWebSocketHandler;
import com.Ntra.PROGIGS.Repository.ContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl {
    @Autowired
    private ContractRepo contractRepo;
    public void notifyFreelancer(Integer contractId) {
        Contract contract = contractRepo.findById(contractId).orElse(null);
        Integer freelancerId = contract.getFreelancer().getId();
        String reviewLink = "http://192.168.0.168:5173/review?contractId=" + contractId;
        String message = "📢 Contract #" + contractId + " closed. Leave your review:\n" + reviewLink;
        CustomWebSocketHandler.sendToFreelancer(freelancerId, message);
    }
}
