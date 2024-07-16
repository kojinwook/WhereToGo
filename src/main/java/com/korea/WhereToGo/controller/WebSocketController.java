package com.korea.WhereToGo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.korea.WhereToGo.dto.request.chat.PostChatMessageRequestDto;
import com.korea.WhereToGo.dto.request.chat.PostTypingStatusRequestDto;
import com.korea.WhereToGo.dto.response.chat.GetChatMessageListResponseDto;
import com.korea.WhereToGo.dto.response.chat.GetChatMessageResponseDto;
import com.korea.WhereToGo.dto.response.chat.GetSavedMessageResponseDto;
import com.korea.WhereToGo.entity.ChatMessageEntity;
import com.korea.WhereToGo.entity.UserStatus;
import com.korea.WhereToGo.repository.ChatMessageRepository;
import com.korea.WhereToGo.service.ChatService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatService chatService;

    @MessageMapping("/chat/{roomId}/message")
    public void postChatMessage(@DestinationVariable String roomId, @Payload PostChatMessageRequestDto dto) {
        chatService.postChatMessage(dto);
        ResponseEntity<? super GetSavedMessageResponseDto> savedMessageResponse = chatService.getSavedMessage(dto.getMessageKey());
        ChatMessageEntity savedMessage = ((GetSavedMessageResponseDto) savedMessageResponse.getBody()).getSavedMessage();
        messagingTemplate.convertAndSend("/topic/chat." + dto.getRoomId(),
                GetChatMessageResponseDto.success(savedMessage));
    }

    @MessageMapping("/chat/room.{roomId}")
    public void getChatMessageList(@Payload Long roomId, Principal principal) {
        System.out.println("Fetching messages for room: " + roomId);
        ResponseEntity<? super GetChatMessageListResponseDto> response = chatService.getChatMessageList(roomId);

        if (response.getBody() == null) {
            System.out.println("Failed to retrieve chat message list");
            return;
        }
        messagingTemplate.convertAndSendToUser(principal.getName(), "/queue/chat." + roomId, response.getBody());
        System.out.println("Messages sent to /queue/chat." + roomId + " for user: " + principal.getName());
    }

    @MessageMapping("/chat/{roomId}/read")
    public void updateReadStatus(@Payload String messageKey) {
        try {
            ResponseEntity<? super GetSavedMessageResponseDto> savedMessageResponse = chatService.getSavedMessage(messageKey);
            if (savedMessageResponse.getBody() == null || !savedMessageResponse.getStatusCode().is2xxSuccessful()) {
                System.out.println("Failed to update read status for messageKey: " + messageKey);
                return;
            }

            ChatMessageEntity updatedMessage = ((GetSavedMessageResponseDto) savedMessageResponse.getBody()).getSavedMessage();
            updatedMessage.setReadByReceiver(true);
            chatMessageRepository.save(updatedMessage);

            messagingTemplate.convertAndSend("/topic/chat." + updatedMessage.getRoomId(), updatedMessage);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @MessageMapping("/chat/{roomId}/typing")
    public void typingStatus(@DestinationVariable String roomId, @Payload PostTypingStatusRequestDto dto) {
        messagingTemplate.convertAndSend("/topic/typing." + roomId, dto);
    }

    // WebSocket 연결 시
    @MessageMapping("/chat/status")
    public void afterConnectionEstablished() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        messagingTemplate.convertAndSend("/topic/status", new UserStatus(username, true));
    }

    // WebSocket 연결 종료 시
    @MessageMapping("/chat/disconnect")
    public void afterConnectionClosed() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        messagingTemplate.convertAndSend("/topic/status", new UserStatus(username, false));
    }

    @MessageMapping("/chat/{roomId}/enter")
    public void userEnterRoom(@DestinationVariable String roomId, @Payload String jsonAuthentication) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            AuthenticationMessage authenticationMessage = objectMapper.readValue(jsonAuthentication, AuthenticationMessage.class);

            String username = authenticationMessage.getUsername();

            String messageToSend = username + " has entered the chat room.";
            messagingTemplate.convertAndSend("/topic/enter." + roomId, messageToSend);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @MessageMapping("/chat/{roomId}/leave")
    public void userLeaveRoom(@DestinationVariable String roomId, @Payload String jsonAuthentication) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            AuthenticationMessage authenticationMessage = objectMapper.readValue(jsonAuthentication, AuthenticationMessage.class);

            String username = authenticationMessage.getUsername();
            List<ChatMessageEntity> chatMessages = chatMessageRepository.findByRoomId(authenticationMessage.getRoomId());

            for (ChatMessageEntity chatMessage : chatMessages) {
                chatMessage.setReadByReceiver(true);
                chatMessageRepository.save(chatMessage);
            }

            String messageToSend = username + " has left the chat room.";
            messagingTemplate.convertAndSend("/topic/leave." + roomId, messageToSend);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Getter
    @Setter
    public static class AuthenticationMessage {
        private Long roomId;
        private String username;
        private String message;
    }
}
