package com.korea.WhereToGo.dto.request.chat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostChatRoomRequestDto {
    private String roomName;
    private String userId;
    private String nickname;
    private String creatorId;
}
