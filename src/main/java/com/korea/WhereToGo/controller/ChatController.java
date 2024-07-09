package com.korea.WhereToGo.controller;

import com.korea.WhereToGo.dto.request.chat.PostChatMessageRequestDto;
import com.korea.WhereToGo.dto.request.chat.PostChatRoomRequestDto;
import com.korea.WhereToGo.dto.response.chat.*;
import com.korea.WhereToGo.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/message")
    public ResponseEntity<? super PostChatMessageResponseDto> postChatMessage(
            @RequestBody PostChatMessageRequestDto messageRequestDto
    ) {
        ResponseEntity<? super PostChatMessageResponseDto> response = chatService.postChatMessage(messageRequestDto);
        return response;
    }

    @GetMapping("/messages/by-room")
    public ResponseEntity<? super GetChatMessageListResponseDto> getChatMessageList(
            @RequestParam Long roomId
    ) {
        ResponseEntity<? super GetChatMessageListResponseDto> response = chatService.getChatMessageList(roomId);
        return response;
    }

    @GetMapping("/messages/by-id")
    public ResponseEntity<? super GetChatMessageResponseDto> getChatMessage(
            @RequestParam Long messageId
    ) {
        ResponseEntity<? super GetChatMessageResponseDto> response = chatService.getChatMessage(messageId);
        return response;
    }

    @PostMapping("/rooms")
    public ResponseEntity<? super PostChatRoomResponseDto> postChatRoom(
            @RequestBody PostChatRoomRequestDto roomRequestDto
    ) {
        ResponseEntity<? super PostChatRoomResponseDto> response = chatService.postChatRoom(roomRequestDto);
        return response;
    }

    @GetMapping("/rooms")
    public ResponseEntity<? super GetChatRoomListResponseDto> getChatRooms() {
        ResponseEntity<? super GetChatRoomListResponseDto> response = chatService.getChatRooms();
        return response;
    }
}
