package com.korea.WhereToGo.service;

import com.korea.WhereToGo.dto.request.chat.PostChatMessageRequestDto;
import com.korea.WhereToGo.dto.request.chat.PostChatRoomRequestDto;
import com.korea.WhereToGo.dto.response.chat.*;
import org.springframework.http.ResponseEntity;

public interface ChatService {
    ResponseEntity<? super PostChatMessageResponseDto> postChatMessage(PostChatMessageRequestDto dto);
    ResponseEntity<? super GetChatMessageResponseDto> getChatMessage(Long messageId);
    ResponseEntity<? super GetChatMessageListResponseDto> getChatMessageList(Long roomId);
    ResponseEntity<? super PostChatRoomResponseDto> postChatRoom(PostChatRoomRequestDto dto);
    ResponseEntity<? super GetChatRoomListResponseDto> getChatRooms();
    ResponseEntity<? super GetSavedMessageResponseDto> getSavedMessage(String messageId);
    ResponseEntity<? super GetChatRoomResponseDto> getChatRoom(String nickname);
}
