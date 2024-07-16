package com.korea.WhereToGo.dto.response.meeting;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.MeetingEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetAllMeetingResponseDto extends ResponseDto {
    private List<MeetingEntity> meetingList;

    private GetAllMeetingResponseDto(List<MeetingEntity> meetingList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.meetingList = meetingList;
    }

    public static ResponseEntity<? super GetAllMeetingResponseDto> success(List<MeetingEntity> meetingList) {
        GetAllMeetingResponseDto responseDto = new GetAllMeetingResponseDto(meetingList);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    public static ResponseEntity<ResponseDto> notExistMeeting() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_MEETING, ResponseMessage.NOT_EXISTED_MEETING);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
