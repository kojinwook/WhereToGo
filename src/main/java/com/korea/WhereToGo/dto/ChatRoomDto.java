package com.korea.WhereToGo.dto;

import com.korea.WhereToGo.entity.ChatMessageEntity;
import com.korea.WhereToGo.entity.ChatRoomEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ChatRoomDto {
    private Long roomId;
    private String roomName;
    private String userId;
    private String nickname;
    private String creatorNickname;
    private UserDto user;
    private UserDto creator;
    private String lastMessage;
    private LocalDateTime lastMessageTimestamp;

    public ChatRoomDto(ChatRoomEntity chatRoom, ChatMessageEntity lastMessageEntity) {
        this.roomId = chatRoom.getRoomId();
        this.roomName = chatRoom.getRoomName();
        this.userId = chatRoom.getUserId();
        this.nickname = chatRoom.getNickname();
        this.creatorNickname = chatRoom.getRoomName();
        this.user = new UserDto(chatRoom.getUser());
        this.creator = new UserDto(chatRoom.getCreator());
        if (lastMessageEntity != null) {
            this.lastMessage = lastMessageEntity.getMessage();
            this.lastMessageTimestamp = lastMessageEntity.getTimestamp();
        }
    }
}
