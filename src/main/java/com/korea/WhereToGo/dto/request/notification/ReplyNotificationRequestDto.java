package com.korea.WhereToGo.dto.request.notification;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReplyNotificationRequestDto {
    private String id;
    private Long meetingId;
    private Long meetingBoardId;
    private String writerId;
    private String replySender;
    private String replyContent;
    private String type;
}