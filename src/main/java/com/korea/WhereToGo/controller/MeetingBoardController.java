package com.korea.WhereToGo.controller;

import com.korea.WhereToGo.dto.request.meeting.board.PatchMeetingBoardRequestDto;
import com.korea.WhereToGo.dto.request.meeting.board.PostMeetingBoardRequestDto;
import com.korea.WhereToGo.dto.request.meeting.board.reply.PostBoardReplyRequestDto;
import com.korea.WhereToGo.dto.request.meeting.board.reply.PostReplyToReplyRequestDto;
import com.korea.WhereToGo.dto.response.meeting.board.*;
import com.korea.WhereToGo.dto.response.meeting.board.reply.GetBoardReplyListResponseDto;
import com.korea.WhereToGo.dto.response.meeting.board.reply.PostBoardReplyResponseDto;
import com.korea.WhereToGo.dto.response.meeting.board.reply.PostReplyToReplyResponseDto;
import com.korea.WhereToGo.service.MeetingBoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meeting/board")
public class MeetingBoardController {

    private final MeetingBoardService meetingBoardService;

    @PostMapping("/{meetingId}")
    public ResponseEntity<? super PostMeetingBoardResponseDto> postMeetingBoard(
            @RequestBody @Valid PostMeetingBoardRequestDto requestBody,
            @PathVariable("meetingId") Long meetingId,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PostMeetingBoardResponseDto> response = meetingBoardService.postMeetingBoard(requestBody, meetingId, userId);
        return response;
    }

    @GetMapping("/list/{meetingId}")
    public ResponseEntity<? super GetMeetingBoardListResponseDto> getMeetingBoardList(
            @PathVariable("meetingId") Long meetingId
    ) {
        ResponseEntity<? super GetMeetingBoardListResponseDto> response = meetingBoardService.getMeetingBoardList(meetingId);

        return response;
    }

    @PatchMapping("/update/{meetingBoardId}")
    public ResponseEntity<? super PatchMeetingBoardResponseDto> patchMeetingBoard(
            @RequestBody @Valid PatchMeetingBoardRequestDto requestBody,
            @PathVariable("meetingBoardId") Long boardId,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PatchMeetingBoardResponseDto> response = meetingBoardService.patchMeetingBoard(requestBody, boardId, userId);
        return response;
    }

    @GetMapping("/detail/{meetingBoardId}")
    public ResponseEntity<? super GetMeetingBoardResponseDto> getMeetingBoard(
            @PathVariable("meetingBoardId") Long boardId
    ) {
        ResponseEntity<? super GetMeetingBoardResponseDto> response = meetingBoardService.getMeetingBoard(boardId);
        return response;
    }

    @DeleteMapping("/delete/{meetingBoardId}")
    public ResponseEntity<? super DeleteMeetingBoardResponseDto> deleteMeetingBoard(
            @PathVariable("meetingBoardId") Long boardId,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super DeleteMeetingBoardResponseDto> response = meetingBoardService.deleteMeetingBoard(boardId, userId);
        return response;
    }

    @PostMapping("/reply")
    public ResponseEntity<? super PostBoardReplyResponseDto> postBoardReply(
            @RequestBody @Valid PostBoardReplyRequestDto requestBody,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PostBoardReplyResponseDto> response = meetingBoardService.postBoardReply(requestBody, userId);
        return response;
    }

    @PostMapping("/reply/reply")
    public ResponseEntity<? super PostReplyToReplyResponseDto> postReplyToReply(
            @RequestBody @Valid PostReplyToReplyRequestDto requestBody,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PostReplyToReplyResponseDto> response = meetingBoardService.postReplyToReply(requestBody, userId);
        return response;
    }

    @GetMapping("/reply/list/{meetingBoardId}")
    public ResponseEntity<? super GetBoardReplyListResponseDto> getBoardReplyList(
            @PathVariable("meetingBoardId") Long boardId
    ) {
        ResponseEntity<? super GetBoardReplyListResponseDto> response = meetingBoardService.getBoardReplyList(boardId);
        return response;
    }
}
