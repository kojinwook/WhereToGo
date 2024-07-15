package com.korea.WhereToGo.service;

import com.korea.WhereToGo.dto.request.meeting.PostMeetingRequestDto;
import com.korea.WhereToGo.dto.response.meeting.GetAllMeetingResponseDto;
import com.korea.WhereToGo.dto.response.meeting.GetMeetingResponseDto;
import com.korea.WhereToGo.dto.response.meeting.PostMeetingResponseDto;
import org.springframework.http.ResponseEntity;

public interface MeetingService {
    ResponseEntity<? super GetMeetingResponseDto> getMeeting(Long meetingId);
    ResponseEntity<? super PostMeetingResponseDto> postMeeting(PostMeetingRequestDto dto);
    ResponseEntity<? super GetAllMeetingResponseDto> getAllMeeting();
}
