package com.korea.WhereToGo.service;

import com.korea.WhereToGo.dto.request.meeting.PatchMeetingRequestDto;
import com.korea.WhereToGo.dto.request.meeting.PostJoinMeetingRequestDto;
import com.korea.WhereToGo.dto.request.meeting.PostMeetingRequestDto;
import com.korea.WhereToGo.dto.response.meeting.*;
import org.springframework.http.ResponseEntity;

public interface MeetingService {
    ResponseEntity<? super GetMeetingResponseDto> getMeeting(Long meetingId);
    ResponseEntity<? super PostMeetingResponseDto> postMeeting(PostMeetingRequestDto dto);
    ResponseEntity<? super GetAllMeetingResponseDto> getAllMeeting();
    ResponseEntity<? super PostJoinMeetingResponseDto> postJoinMeeting(PostJoinMeetingRequestDto dto);
    ResponseEntity<? super PostResponseToJoinResponseDto> respondToJoinRequest(Long requestId, boolean status);
    ResponseEntity<? super GetMeetingRequestsResponseDto> getMeetingRequests(Long meetingId);
    ResponseEntity<? super PatchMeetingResponseDto> patchMeeting(PatchMeetingRequestDto dto, Long meetingId, String userId);
}
