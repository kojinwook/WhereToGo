package com.korea.WhereToGo.controller;

import com.korea.WhereToGo.dto.request.meeting.PostMeetingRequestDto;
import com.korea.WhereToGo.dto.response.meeting.PostMeetingResponseDto;
import com.korea.WhereToGo.entity.MeetingEntity;
import com.korea.WhereToGo.service.MeetingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meeting")
public class MeetingController {

    private final MeetingService meetingService;

    @PostMapping("/write")
    public ResponseEntity<? super PostMeetingResponseDto> postMeeting(
            @RequestBody @Valid PostMeetingRequestDto requestBody){
        ResponseEntity<? super PostMeetingResponseDto> response = meetingService.postMeeting(requestBody);
        return response;
    }
}
