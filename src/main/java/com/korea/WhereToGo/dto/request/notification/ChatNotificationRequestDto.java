package com.korea.WhereToGo.dto.request.notification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatNotificationRequestDto {
    private String id;
    private Long chatRoomId;
    private String senderId;
    private String receiverId;
    private String message;
}
