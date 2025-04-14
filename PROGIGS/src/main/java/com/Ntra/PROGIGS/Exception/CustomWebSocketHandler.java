package com.Ntra.PROGIGS.Exception;

import com.Ntra.PROGIGS.DTOs.NotificationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.ConcurrentHashMap;

public class CustomWebSocketHandler extends TextWebSocketHandler {
    public static ConcurrentHashMap<Integer, WebSocketSession> freelancerSessions = new ConcurrentHashMap<>();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        Integer freelancerId = extractFreelancerId(session); // Custom logic
        if (freelancerId != null) {
            freelancerSessions.put(freelancerId, session);
        }
    }

    // Fix in extractFreelancerId
    private Integer extractFreelancerId(WebSocketSession session) {
        String query = session.getUri().getQuery(); // e.g. userId=3
        if (query != null && query.startsWith("userId=")) {
            return Integer.valueOf(query.split("=")[1]); // ✅ Fix from Integer.getInteger()
        }
        return null;
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        freelancerSessions.values().remove(session); // remove session on disconnect
    }

    public static void sendToFreelancer(Integer freelancerId, NotificationDto message) {
        WebSocketSession session = freelancerSessions.get(freelancerId);
        if (session != null && session.isOpen()) {
            try {
                String jsonMessage = objectMapper.writeValueAsString(message);
                session.sendMessage(new TextMessage(jsonMessage));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
