package com.korea.WhereToGo.service;

import com.korea.WhereToGo.dto.request.meeting.*;
import com.korea.WhereToGo.dto.response.meeting.*;
import org.springframework.http.ResponseEntity;

public interface MeetingService {
    ResponseEntity<? super GetMeetingResponseDto> getMeeting(Long meetingId);
    ResponseEntity<? super PostMeetingResponseDto> postMeeting(PostMeetingRequestDto dto, String userId);
    ResponseEntity<? super GetAllMeetingResponseDto> getAllMeeting();
    ResponseEntity<? super PostJoinMeetingResponseDto> postJoinMeeting(PostJoinMeetingRequestDto dto);
    ResponseEntity<? super PostResponseToJoinResponseDto> respondToJoinRequest(Long requestId, boolean status);
    ResponseEntity<? super GetMeetingRequestsResponseDto> getMeetingRequests(Long meetingId);
    ResponseEntity<? super PatchMeetingResponseDto> patchMeeting(PatchMeetingRequestDto dto, Long meetingId, String userId);
    ResponseEntity<? super GetJoinMeetingMemberResponseDto> getJoinMeetingMember(Long meetingId);
    ResponseEntity<? super GetUserMeetingResponseDto> getUserMeeting(String userId);
    ResponseEntity<? super DeleteMeetingResponseDto> deleteMeeting(Long meetingId, String userId);
    ResponseEntity<? super Get5RecentMeetingResponseDto> get5RecentMeeting();
}
