package com.korea.WhereToGo.dto.request.notification;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatNotificationRequestDto {
    private String id;
    private Long chatRoomId;
    private String senderId;
    private String receiverId;
    private String message;
    private String type;
}
