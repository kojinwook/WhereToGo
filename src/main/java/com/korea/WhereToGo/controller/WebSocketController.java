package com.korea.WhereToGo.controller;

import com.korea.WhereToGo.dto.request.chat.PostChatMessageRequestDto;
import com.korea.WhereToGo.dto.response.chat.GetChatMessageListResponseDto;
import com.korea.WhereToGo.dto.response.chat.GetChatMessageResponseDto;
import com.korea.WhereToGo.dto.response.chat.GetSavedMessageResponseDto;
import com.korea.WhereToGo.entity.ChatMessageEntity;
import com.korea.WhereToGo.repository.ChatMessageRepository;
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


//    @MessageMapping("/chat/{roomId}/read")
//    public void updateReadStatus(@Payload Long messageId) {
//        try {
//            ResponseEntity<? super UpdateReadStatusResponseDto> response = chatService.updateReadStatus(messageId);
//            if (response.getBody() == null || !response.getStatusCode().is2xxSuccessful()) {
//                System.out.println("Failed to update read status for message: " + messageId);
//                return;
//            }
//
//            // ChatMessageEntity 정보 추출
//            GetChatMessageResponseDto chatMessageResponse = (GetChatMessageResponseDto) response.getBody();
//            ChatMessageEntity updatedMessage = chatMessageResponse.getChatMessage();
//
//            // 채팅 메시지를 해당 토픽으로 전송
//            messagingTemplate.convertAndSend("/topic/chat." + updatedMessage.getRoomId(), updatedMessage);
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//    }
}
