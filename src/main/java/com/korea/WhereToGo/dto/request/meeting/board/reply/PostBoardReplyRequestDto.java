package com.korea.WhereToGo.dto.request.meeting.board.reply;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostBoardReplyRequestDto {
    private String reply;
    private Long meetingBoardId;
    private Long meetingId;
}
