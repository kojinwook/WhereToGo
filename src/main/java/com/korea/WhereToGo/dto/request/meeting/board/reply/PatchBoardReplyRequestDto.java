package com.korea.WhereToGo.dto.request.meeting.board.reply;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchBoardReplyRequestDto {
    private Long replyId;
    private String reply;
}
