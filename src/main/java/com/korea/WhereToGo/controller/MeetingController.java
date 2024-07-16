package com.korea.WhereToGo.controller;

import com.korea.WhereToGo.dto.request.meeting.PostJoinMeetingRequestDto;
import com.korea.WhereToGo.dto.request.meeting.PostMeetingRequestDto;
import com.korea.WhereToGo.dto.response.meeting.GetAllMeetingResponseDto;
import com.korea.WhereToGo.dto.response.meeting.GetMeetingResponseDto;
import com.korea.WhereToGo.dto.response.meeting.PostJoinMeetingResponseDto;
import com.korea.WhereToGo.dto.response.meeting.PostMeetingResponseDto;
import com.korea.WhereToGo.service.MeetingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meeting")
public class MeetingController {

    private final MeetingService meetingService;

    @PostMapping("/write")
    public ResponseEntity<? super PostMeetingResponseDto> postMeeting(
            @RequestBody @Valid PostMeetingRequestDto requestBody
    ) {
        ResponseEntity<? super PostMeetingResponseDto> response = meetingService.postMeeting(requestBody);
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
}
