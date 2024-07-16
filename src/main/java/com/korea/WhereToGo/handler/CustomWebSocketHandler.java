package com.korea.WhereToGo.handler;

import com.korea.WhereToGo.entity.UserStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CustomWebSocketHandler implements WebSocketHandler {

    private final ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    private final SimpMessagingTemplate messagingTemplate;

    public CustomWebSocketHandler(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Connection established");
        String username = session.getPrincipal().getName();
        sessions.put(username, session);
        messagingTemplate.convertAndSend("/topic/status", new UserStatus(username, true));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // Handle transport errors
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String username = session.getPrincipal().getName();
        sessions.remove(username);
        messagingTemplate.convertAndSend("/topic/status", new UserStatus(username, false));
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public void sendToUser(String username, Object payload) throws IOException {
        WebSocketSession session = sessions.get(username);
        if (session != null && session.isOpen()) {
            session.sendMessage(new TextMessage(payload.toString()));
        }
    }
}
