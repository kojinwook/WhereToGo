package com.korea.WhereToGo.dto.request.meeting.board.reply;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostReplyToReplyRequestDto {
    private String replyReply;
    private Long parentCommentId;
}
