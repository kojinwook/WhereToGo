package com.korea.WhereToGo.controller;

import com.korea.WhereToGo.dto.request.meeting.PatchMeetingRequestDto;
import com.korea.WhereToGo.dto.request.meeting.PostJoinMeetingRequestDto;
import com.korea.WhereToGo.dto.request.meeting.PostMeetingBoardRequestDto;
import com.korea.WhereToGo.dto.request.meeting.PostMeetingRequestDto;
import com.korea.WhereToGo.dto.response.meeting.*;
import com.korea.WhereToGo.service.MeetingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meeting")
public class MeetingController {

    private final MeetingService meetingService;

    @PostMapping("/write")
    public ResponseEntity<? super PostMeetingResponseDto> postMeeting(
            @RequestBody @Valid PostMeetingRequestDto requestBody,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PostMeetingResponseDto> response = meetingService.postMeeting(requestBody, userId);
        return response;
    }

    @GetMapping("/list")
    public ResponseEntity<? super GetAllMeetingResponseDto> getAllMeeting() {
        ResponseEntity<? super GetAllMeetingResponseDto> response = meetingService.getAllMeeting();
        return response;
    }

    @GetMapping("/detail/{meetingId}")
    public ResponseEntity<? super GetMeetingResponseDto> getMeeting(
            @PathVariable Long meetingId
    ) {
        ResponseEntity<? super GetMeetingResponseDto> response = meetingService.getMeeting(meetingId);
        return response;
    }

    @PostMapping("/join")
    public ResponseEntity<? super PostJoinMeetingResponseDto> postJoinMeeting(
            @RequestBody @Valid PostJoinMeetingRequestDto requestBody
    ) {
        ResponseEntity<? super PostJoinMeetingResponseDto> response = meetingService.postJoinMeeting(requestBody);
        return response;
    }

    @PostMapping("/response")
    public ResponseEntity<? super PostResponseToJoinResponseDto> respondToJoinRequest(
            @RequestParam Long requestId,
            @RequestParam boolean status
    ) {
        ResponseEntity<? super PostResponseToJoinResponseDto> response = meetingService.respondToJoinRequest(requestId, status);
        return response;
    }

    @GetMapping("/requests")
    public ResponseEntity<? super GetMeetingRequestsResponseDto> getMeetingRequests(
            @RequestParam Long meetingId
    ) {
        ResponseEntity<? super GetMeetingRequestsResponseDto> response = meetingService.getMeetingRequests(meetingId);
        return response;
    }

    @PatchMapping("/update/{meetingId}")
    public ResponseEntity<? super PatchMeetingResponseDto> patchMeeting(
            @RequestBody @Valid PatchMeetingRequestDto requestBody,
            @PathVariable("meetingId") Long meetingId,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PatchMeetingResponseDto> response = meetingService.patchMeeting(requestBody, meetingId, userId);
        return response;
    }

    @PostMapping("/board/{meetingId}")
    public ResponseEntity<? super PostMeetingBoardResponseDto> postMeetingBoard(
            @RequestBody @Valid PostMeetingBoardRequestDto requestBody,
            @PathVariable("meetingId") Long meetingId,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PostMeetingBoardResponseDto> response = meetingService.postMeetingBoard(requestBody, meetingId, userId);
        return response;
    }

    @GetMapping("/board/list/{meetingId}")
    public ResponseEntity<? super GetMeetingBoardListResponseDto> getMeetingBoardList(
            @PathVariable("meetingId") Long meetingId
    ) {
        ResponseEntity<? super GetMeetingBoardListResponseDto> response = meetingService.getMeetingBoardList(meetingId);

        return response;
    }
}
