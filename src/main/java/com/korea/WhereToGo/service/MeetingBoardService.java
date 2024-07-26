package com.korea.WhereToGo.service;

import com.korea.WhereToGo.dto.request.meeting.board.PatchMeetingBoardRequestDto;
import com.korea.WhereToGo.dto.request.meeting.board.PostMeetingBoardRequestDto;
import com.korea.WhereToGo.dto.response.meeting.GetMeetingImageListResponseDto;
import com.korea.WhereToGo.dto.response.meeting.board.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MeetingBoardService {
    ResponseEntity<? super PostMeetingBoardResponseDto> postMeetingBoard(PostMeetingBoardRequestDto dto, Long meetingId, String userId);
    ResponseEntity<? super PatchMeetingBoardResponseDto> patchMeetingBoard(PatchMeetingBoardRequestDto dto, Long boardId, String userId);
    ResponseEntity<? super GetMeetingBoardListResponseDto> getMeetingBoardList(Long meetingId);
    ResponseEntity<? super GetMeetingBoardResponseDto> getMeetingBoard(Long boardId);
    ResponseEntity<? super GetUserBoardResponseDto> getUserBoard(String userId);
    ResponseEntity<? super DeleteMeetingBoardResponseDto> deleteMeetingBoard(Long boardId, String userId);
    ResponseEntity<? super GetMeetingImageListResponseDto> getMeetingImageList(Long meetingId);
    ResponseEntity<? super GetMeetingBoardsTitleResponseDto> getMeetingBoardsTitle(List<Long> meetingBoardIds);
}
