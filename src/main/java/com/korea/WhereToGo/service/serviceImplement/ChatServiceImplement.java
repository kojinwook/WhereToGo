package com.korea.WhereToGo.service.serviceImplement;

import com.korea.WhereToGo.dto.request.chat.PostChatMessageRequestDto;
import com.korea.WhereToGo.dto.request.chat.PostChatRoomRequestDto;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.dto.response.chat.*;
import com.korea.WhereToGo.entity.ChatMessageEntity;
import com.korea.WhereToGo.entity.ChatRoomEntity;
import com.korea.WhereToGo.repository.ChatMessageRepository;
import com.korea.WhereToGo.repository.ChatRoomRepository;
import com.korea.WhereToGo.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImplement implements ChatService {

    private final SimpMessagingTemplate messagingTemplate;
    public final ChatRoomRepository chatRoomRepository;
    public final ChatMessageRepository chatMessageRepository;

    @Override
    public ResponseEntity<? super PostChatMessageResponseDto> postChatMessage(PostChatMessageRequestDto dto) {
        Long roomId = dto.getRoomId();
        try {
            ChatRoomEntity chatRoom = chatRoomRepository.findByRoomId(roomId);
            if (chatRoom == null) return PostChatMessageResponseDto.notExistChatRoom();

            ChatMessageEntity message = new ChatMessageEntity();
            message.setRoomId(roomId);
            message.setMessageKey(dto.getMessageKey());
            message.setSender(dto.getSender());
            message.setMessage(dto.getMessage());
            message.setTimestamp(LocalDateTime.now());
            chatMessageRepository.save(message);

            messagingTemplate.convertAndSend("/topic/chat/" + dto.getRoomId(), new GetChatMessageResponseDto(message));

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostChatMessageResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetChatMessageResponseDto> getChatMessage(Long messageId) {
        ChatMessageEntity message = new ChatMessageEntity();
        try {
            message = chatMessageRepository.findByMessageId(messageId);
            if (message == null) return GetChatMessageResponseDto.notExistChatMessage();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetChatMessageResponseDto.success(message);
    }

    @Override
    public ResponseEntity<? super GetChatMessageListResponseDto> getChatMessageList(Long roomId) {
        List<ChatMessageEntity> messages = new ArrayList<>();
        try {
            messages = chatMessageRepository.findByRoomId(roomId);
            if (messages.isEmpty()) return GetChatMessageListResponseDto.notExistChatRoom();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return GetChatMessageListResponseDto.success(messages);
    }

    @Override
    public ResponseEntity<? super PostChatRoomResponseDto> postChatRoom(PostChatRoomRequestDto dto) {
        String roomName = dto.getRoomName();
        try {
            ChatRoomEntity room = chatRoomRepository.findByRoomName(roomName);
            if (room != null) return PostChatRoomResponseDto.alreadyExistChatRoom();

            ChatRoomEntity chatRoom = new ChatRoomEntity();
            chatRoom.setRoomName(roomName);
            chatRoomRepository.save(chatRoom);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }

        return PostChatRoomResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetChatRoomListResponseDto> getChatRooms() {
        List<ChatRoomEntity> chatRooms = new ArrayList<>();
        try {
            chatRooms = chatRoomRepository.findAll();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetChatRoomListResponseDto.success(chatRooms);
    }

    @Override
    public ResponseEntity<? super GetSavedMessageResponseDto> getSavedMessage(String messageKey) {
        ChatMessageEntity message = new ChatMessageEntity();
        try{
            message = chatMessageRepository.findByMessageKey(messageKey);
            if (message == null) return GetSavedMessageResponseDto.notExistChatMessage();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetSavedMessageResponseDto.success(message);
    }

    @Override
    public ResponseEntity<? super GetChatRoomResponseDto> getChatRoom(String userId) {
        List<ChatRoomEntity> chatRooms = new ArrayList<>();
        try {
            chatRooms = chatRoomRepository.findByUserId(userId);
            if (chatRooms == null) return GetChatRoomResponseDto.notExistChatRoom();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetChatRoomResponseDto.success(chatRooms);
    }
}
