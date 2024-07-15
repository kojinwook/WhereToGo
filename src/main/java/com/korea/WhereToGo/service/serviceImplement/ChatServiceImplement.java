package com.korea.WhereToGo.service.serviceImplement;

import com.korea.WhereToGo.dto.UserDto;
import com.korea.WhereToGo.dto.request.chat.PostChatMessageRequestDto;
import com.korea.WhereToGo.dto.request.chat.PostChatRoomRequestDto;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.dto.response.chat.*;
import com.korea.WhereToGo.entity.ChatMessageEntity;
import com.korea.WhereToGo.entity.ChatRoomEntity;
import com.korea.WhereToGo.entity.UserEntity;
import com.korea.WhereToGo.repository.ChatMessageRepository;
import com.korea.WhereToGo.repository.ChatRoomRepository;
import com.korea.WhereToGo.repository.UserRepository;
import com.korea.WhereToGo.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatServiceImplement implements ChatService {

    private final SimpMessagingTemplate messagingTemplate;
    public final ChatRoomRepository chatRoomRepository;
    public final ChatMessageRepository chatMessageRepository;
    public final UserRepository userRepository;

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

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetChatMessageListResponseDto.success(messages);
    }

    @Override
    public ResponseEntity<? super PostChatRoomResponseDto> postChatRoom(PostChatRoomRequestDto dto) {
        ChatRoomEntity chatRoom = new ChatRoomEntity();
        Long roomId = null;
        try {
            Optional<ChatRoomEntity> existingChatRoom = chatRoomRepository.findByNicknameAndCreatorNickname(dto.getNickname(), dto.getCreatorNickname());
            if (existingChatRoom.isPresent()) {
                roomId = existingChatRoom.get().getRoomId();
                return PostChatRoomResponseDto.success(roomId);
            }

            chatRoom.setRoomName(dto.getRoomName());
            chatRoom.setUserId(dto.getUserId());
            chatRoom.setNickname(dto.getNickname());
            chatRoom.setCreatorNickname(dto.getCreatorNickname());
            chatRoomRepository.save(chatRoom);

            roomId = chatRoom.getRoomId();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostChatRoomResponseDto.success(roomId);
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

    public ResponseEntity<? super GetChatRoomResponseDto> getUserChatRooms(String nickname) {
        List<ChatRoomEntity> chatRoomList = new ArrayList<>();
        try {
            chatRoomList = chatRoomRepository.findByNicknameOrCreatorNickname(nickname, nickname);
            if (chatRoomList == null || chatRoomList.isEmpty()) {
                return GetChatRoomResponseDto.notExistChatRoom();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetChatRoomResponseDto.success(chatRoomList);
    }

    @Override
    public ResponseEntity<? super GetRoomUsersResponseDto> getRoomUsers(Long roomId) {
        List<UserDto> users = new ArrayList<>();
        try {
            ChatRoomEntity chatRoom = chatRoomRepository.findByRoomId(roomId);
            if (chatRoom == null) return GetRoomUsersResponseDto.notExistChatRoom();

            UserEntity user1 = userRepository.findByNickname(chatRoom.getNickname());
            UserEntity user2 = userRepository.findByNickname(chatRoom.getCreatorNickname());

            if (user1 != null) {
                users.add(new UserDto(user1.getUserId(), user1.getNickname(), user1.getProfileImage()));
            }
            if (user2 != null) {
                users.add(new UserDto(user2.getUserId(), user2.getNickname(), user2.getProfileImage()));
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetRoomUsersResponseDto.success(users);
    }

}
