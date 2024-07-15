package com.korea.WhereToGo.dto.request.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostChatRoomRequestDto {
    private String roomName;
    private String userId;
    private String nickname;
    private String creatorNickname;
}
