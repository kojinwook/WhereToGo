package com.korea.WhereToGo.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@Slf4j
public class WebSocketChatHandler extends TextWebSocketHandler {

    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketChatHandler(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 클라이언트로부터 메시지 수신 처리
        log.info("Received message: " + message.getPayload());

        // 예제: 수신한 메시지를 다시 클라이언트에게 전송
        messagingTemplate.convertAndSend("/topic/messages", message.getPayload());
    }
}

