package com.korea.WhereToGo.dto.request.meeting.board.reply;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostBoardReplyRequestDto {
    private String reply;
    private Long meetingBoardId;
    private Long meetingId;
}
