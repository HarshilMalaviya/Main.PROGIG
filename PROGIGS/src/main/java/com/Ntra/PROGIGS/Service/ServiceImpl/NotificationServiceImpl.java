package com.Ntra.PROGIGS.Service.ServiceImpl;

import com.Ntra.PROGIGS.Exception.CustomWebSocketHandler;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl {
    public void notifyFreelancer(Integer freelancerId, Integer contractId) {
        String reviewLink = "http://192.168.0.168:5173/employer/contracts/review?contractId=" + contractId;
        String message = "📢 Contract #" + contractId + " closed. Leave your review:\n" + reviewLink;
        CustomWebSocketHandler.sendToFreelancer(freelancerId, message);
    }
}
