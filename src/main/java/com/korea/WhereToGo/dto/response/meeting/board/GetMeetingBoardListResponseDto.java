package com.korea.WhereToGo.dto.response.meeting.board;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.MeetingBoardEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetMeetingBoardListResponseDto extends ResponseDto {

    private List<MeetingBoardEntity> meetingBoardList;

    public GetMeetingBoardListResponseDto(List<MeetingBoardEntity> meetingBoardList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.meetingBoardList = meetingBoardList;
    }

    public static ResponseEntity<? super GetMeetingBoardListResponseDto> success(List<MeetingBoardEntity> meetingBoardList) {
        GetMeetingBoardListResponseDto responseBody = new GetMeetingBoardListResponseDto(meetingBoardList);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
