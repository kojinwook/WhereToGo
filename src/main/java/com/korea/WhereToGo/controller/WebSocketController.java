package com.korea.WhereToGo.controller;

import com.korea.WhereToGo.dto.request.chat.PostChatMessageRequestDto;
import com.korea.WhereToGo.dto.response.chat.GetChatMessageListResponseDto;
import com.korea.WhereToGo.dto.response.chat.GetChatMessageResponseDto;
import com.korea.WhereToGo.dto.response.chat.GetSavedMessageResponseDto;
import com.korea.WhereToGo.entity.ChatMessageEntity;
import com.korea.WhereToGo.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;
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
}
