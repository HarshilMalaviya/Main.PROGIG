package com.Ntra.PROGIGS.Exception;

import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.ConcurrentHashMap;

public class CustomWebSocketHandler extends TextWebSocketHandler {
    public static ConcurrentHashMap<Integer, WebSocketSession> freelancerSessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        Integer freelancerId = extractFreelancerId(session); // Custom logic
        if (freelancerId != null) {
            freelancerSessions.put(freelancerId, session);
        }
    }

    private Integer extractFreelancerId(WebSocketSession session) {
        // Example: ws://host/ws?userId=123
        String query = session.getUri().getQuery(); // userId=123
        if (query != null && query.startsWith("userId=")) {
            return Integer.getInteger(query.split("=")[1]);
        }
        return null;
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        freelancerSessions.values().remove(session); // remove session on disconnect
    }

    public static void sendToFreelancer(Integer freelancerId, String message) {
        WebSocketSession session = freelancerSessions.get(freelancerId);
        if (session != null && session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
