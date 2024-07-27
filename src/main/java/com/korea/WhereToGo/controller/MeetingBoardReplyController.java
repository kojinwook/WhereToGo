package com.korea.WhereToGo.controller;

import com.korea.WhereToGo.dto.request.meeting.board.reply.PatchBoardReplyRequestDto;
import com.korea.WhereToGo.dto.request.meeting.board.reply.PatchReplyReplyRequestDto;
import com.korea.WhereToGo.dto.request.meeting.board.reply.PostBoardReplyRequestDto;
import com.korea.WhereToGo.dto.request.meeting.board.reply.PostReplyToReplyRequestDto;
import com.korea.WhereToGo.dto.response.meeting.board.reply.*;
import com.korea.WhereToGo.service.MeetingBoardReplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meeting/board/reply")
public class MeetingBoardReplyController {

    private final MeetingBoardReplyService meetingBoardReplyService;

    @PostMapping("")
    public ResponseEntity<? super PostBoardReplyResponseDto> postBoardReply(
            @RequestBody @Valid PostBoardReplyRequestDto requestBody,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PostBoardReplyResponseDto> response = meetingBoardReplyService.postBoardReply(requestBody, userId);
        return response;
    }

    @PostMapping("/reply")
    public ResponseEntity<? super PostReplyToReplyResponseDto> postReplyToReply(
            @RequestBody @Valid PostReplyToReplyRequestDto requestBody,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PostReplyToReplyResponseDto> response = meetingBoardReplyService.postReplyToReply(requestBody, userId);
        return response;
    }

    @GetMapping("/list/{meetingBoardId}")
    public ResponseEntity<? super GetBoardReplyListResponseDto> getBoardReplyList(
            @PathVariable("meetingBoardId") Long boardId
    ) {
        ResponseEntity<? super GetBoardReplyListResponseDto> response = meetingBoardReplyService.getBoardReplyList(boardId);
        return response;
    }

    @DeleteMapping("/delete/{replyId}")
    public ResponseEntity<? super DeleteBoardReplyResponseDto> deleteBoardReply(
            @PathVariable("replyId") Long replyId,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super DeleteBoardReplyResponseDto> response = meetingBoardReplyService.deleteBoardReply(replyId, userId);
        return response;
    }

    @DeleteMapping("/reply/delete/{replyReplyId}")
    public ResponseEntity<? super DeleteReplyReplyResponseDto> deleteReplyReply(
            @PathVariable("replyReplyId") Long replyReplyId,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super DeleteReplyReplyResponseDto> response = meetingBoardReplyService.deleteReplyReply(replyReplyId, userId);
        return response;
    }

    @PatchMapping("/patch")
    public ResponseEntity<? super PatchBoardReplyResponseDto> patchBoardReply(
            @RequestBody @Valid PatchBoardReplyRequestDto requestBody,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PatchBoardReplyResponseDto> response = meetingBoardReplyService.patchBoardReply(requestBody, userId);
        return response;
    }

    @PatchMapping("/reply/patch")
    public ResponseEntity<? super PatchReplyReplyResponseDto> patchReplyReply(
            @RequestBody @Valid PatchReplyReplyRequestDto requestBody,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PatchReplyReplyResponseDto> response = meetingBoardReplyService.patchReplyReply(requestBody, userId);
        return response;
    }
}
