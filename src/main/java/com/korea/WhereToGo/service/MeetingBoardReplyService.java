package com.korea.WhereToGo.service;

import com.korea.WhereToGo.dto.request.meeting.board.reply.PatchBoardReplyRequestDto;
import com.korea.WhereToGo.dto.request.meeting.board.reply.PatchReplyReplyRequestDto;
import com.korea.WhereToGo.dto.request.meeting.board.reply.PostBoardReplyRequestDto;
import com.korea.WhereToGo.dto.request.meeting.board.reply.PostReplyToReplyRequestDto;
import com.korea.WhereToGo.dto.response.meeting.board.reply.*;
import org.springframework.http.ResponseEntity;

public interface MeetingBoardReplyService {
    ResponseEntity<? super PostBoardReplyResponseDto> postBoardReply(PostBoardReplyRequestDto dto, String userId);
    ResponseEntity<? super GetBoardReplyListResponseDto> getBoardReplyList(Long boardId);
    ResponseEntity<? super PostReplyToReplyResponseDto> postReplyToReply(PostReplyToReplyRequestDto dto, String userId);
    ResponseEntity<? super DeleteBoardReplyResponseDto> deleteBoardReply(Long replyId, String userId);
    ResponseEntity<? super DeleteReplyReplyResponseDto> deleteReplyReply(Long replyReplyId, String userId);
    ResponseEntity<? super PatchBoardReplyResponseDto> patchBoardReply(PatchBoardReplyRequestDto dto, String userId);
    ResponseEntity<? super PatchReplyReplyResponseDto> patchReplyReply(PatchReplyReplyRequestDto dto, String userId);
}
