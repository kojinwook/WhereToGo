package com.korea.WhereToGo.controller;

import com.korea.WhereToGo.dto.request.chat.PostChatMessageRequestDto;
import com.korea.WhereToGo.dto.response.chat.GetChatMessageListResponseDto;
import com.korea.WhereToGo.dto.response.chat.GetChatMessageResponseDto;
import com.korea.WhereToGo.dto.response.chat.GetSavedMessageResponseDto;
import com.korea.WhereToGo.entity.ChatMessageEntity;
import com.korea.WhereToGo.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @MessageMapping("/chat/message")
    public void postChatMessage(@Payload PostChatMessageRequestDto dto) {
        chatService.postChatMessage(dto);

        ResponseEntity<? super GetSavedMessageResponseDto> savedMessageResponse = chatService.getSavedMessage(dto.getMessageKey());

        ChatMessageEntity savedMessage = ((GetSavedMessageResponseDto) savedMessageResponse.getBody()).getSavedMessage();
        messagingTemplate.convertAndSend("/topic/chat." + dto.getRoomId(),
                GetChatMessageResponseDto.success(savedMessage));
    }

    @MessageMapping("/chat/room.{roomId}")
    public void getChatMessageList(@Payload Long roomId, Principal principal) {
        ResponseEntity<? super GetChatMessageListResponseDto> response = chatService.getChatMessageList(roomId);

        messagingTemplate.convertAndSendToUser(principal.getName(), "/queue/chat." + roomId, response.getBody());
    }
}
