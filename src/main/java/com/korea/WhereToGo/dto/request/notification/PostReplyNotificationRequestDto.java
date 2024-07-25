package com.korea.WhereToGo.dto.request.notification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostReplyNotificationRequestDto {
    private Long meetingBoardId;
    private String writerId;
    private String replySender;
    private String replyContent;
}