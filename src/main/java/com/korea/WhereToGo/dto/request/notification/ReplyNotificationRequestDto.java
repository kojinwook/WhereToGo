package com.korea.WhereToGo.dto.request.notification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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