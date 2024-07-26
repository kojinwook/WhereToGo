package com.korea.WhereToGo.dto.response.meeting;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.MeetingDto;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetUserMeetingResponseDto extends ResponseDto {

    private List<MeetingDto> meetingList;

    public GetUserMeetingResponseDto(List<MeetingDto> meetingList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.meetingList = meetingList;
    }

    public static ResponseEntity<? super GetUserMeetingResponseDto> success(List<MeetingDto> meetingList) {
        GetUserMeetingResponseDto responseBody = new GetUserMeetingResponseDto(meetingList);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistedMeeting() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_MEETING, ResponseMessage.NOT_EXISTED_MEETING);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
