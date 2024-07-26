package com.korea.WhereToGo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.korea.WhereToGo.dto.request.chat.PostChatMessageRequestDto;
import com.korea.WhereToGo.dto.request.meeting.board.reply.PostBoardReplyRequestDto;
import com.korea.WhereToGo.dto.request.notification.ChatNotificationRequestDto;
import com.korea.WhereToGo.dto.request.notification.ReplyNotificationRequestDto;
import com.korea.WhereToGo.dto.response.chat.GetChatMessageListResponseDto;
import com.korea.WhereToGo.dto.response.chat.GetChatMessageResponseDto;
import com.korea.WhereToGo.dto.response.chat.GetSavedMessageResponseDto;
import com.korea.WhereToGo.entity.ChatMessageEntity;
import com.korea.WhereToGo.entity.MeetingBoardEntity;
import com.korea.WhereToGo.entity.UserEntity;
import com.korea.WhereToGo.entity.UserStatus;
import com.korea.WhereToGo.repository.ChatMessageRepository;
import com.korea.WhereToGo.repository.MeetingBoardRepository;
import com.korea.WhereToGo.repository.UserRepository;
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
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class WebSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageRepository chatMessageRepository;
    private final MeetingBoardRepository meetingBoardRepository;
    private final UserRepository userRepository;
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

    @MessageMapping("/chat/status")
    public void afterConnectionEstablished() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        messagingTemplate.convertAndSend("/topic/status", new UserStatus(username, true));
    }

    @MessageMapping("/chat/disconnect")
    public void afterConnectionClosed() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        messagingTemplate.convertAndSend("/topic/status", new UserStatus(username, false));
    }

    @MessageMapping("/board/reply")
    public void notifyPostReply(@Payload PostBoardReplyRequestDto dto,
                                @Payload String jsonAuthentication) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            AuthenticationMessage authenticationMessage = objectMapper.readValue(jsonAuthentication, AuthenticationMessage.class);

            String username = authenticationMessage.getReplySender();

            MeetingBoardEntity meetingBoard = meetingBoardRepository.findByMeetingBoardId(dto.getMeetingBoardId());
            UserEntity writer = meetingBoard.getUser();

            UserEntity postAuthor = userRepository.findByUserId(writer.getUserId());
            if (postAuthor != null) {
                ReplyNotificationRequestDto notification = new ReplyNotificationRequestDto();
                notification.setId(UUID.randomUUID().toString());
                notification.setMeetingId(meetingBoard.getMeeting().getMeetingId());
                notification.setMeetingBoardId(dto.getMeetingBoardId());
                notification.setWriterId(postAuthor.getUserId());
                notification.setReplySender(username);
                notification.setReplyContent(authenticationMessage.getReplyContent());

                messagingTemplate.convertAndSend("/topic/notifications/" + postAuthor.getUserId(), notification);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    @MessageMapping("/chat/message")
    public void notifyChatMessage(@Payload PostChatMessageRequestDto dto) {
        try {
            ChatMessageEntity chatMessage = chatMessageRepository.findByMessageKey(dto.getMessageKey());
            if (chatMessage == null) {
                System.out.println("Failed to retrieve chat message with messageKey: " + dto.getMessageKey());
                return;
            }
                ChatNotificationRequestDto notification = new ChatNotificationRequestDto();
                notification.setId(UUID.randomUUID().toString());
                notification.setChatRoomId(chatMessage.getRoomId());
                notification.setSenderId(chatMessage.getSender());
                notification.setMessage(chatMessage.getMessage());

                messagingTemplate.convertAndSend("/topic/notifications." + chatMessage.getRoomId(), notification);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw exception;
        }
    }

    @Getter
    @Setter
    public static class AuthenticationMessage {
        private Long meetingBoardId;
        private String replySender;
        private String replyContent;
        private Long roomId;
        private String messageSender;

    }
}
