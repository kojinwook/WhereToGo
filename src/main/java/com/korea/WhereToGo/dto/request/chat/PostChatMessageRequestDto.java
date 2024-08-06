package com.korea.WhereToGo.dto.request.chat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostChatMessageRequestDto {
    private Long roomId; // 채팅방 ID
    private String sender; // 발신자
    private String message; // 메시지 내용
    private String messageKey; // 메시지 식별키
}
