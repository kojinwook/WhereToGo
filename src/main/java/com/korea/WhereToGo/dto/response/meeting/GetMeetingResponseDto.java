package com.korea.WhereToGo.dto.response.meeting;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.MeetingEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetMeetingResponseDto extends ResponseDto {
    private MeetingEntity meeting;

    private GetMeetingResponseDto(MeetingEntity meeting) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.meeting = meeting;
    }

    public  static ResponseEntity<GetMeetingResponseDto> success(MeetingEntity meeting){
        GetMeetingResponseDto responseDto = new GetMeetingResponseDto(meeting);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    public static ResponseEntity<ResponseDto> notExistMeeting() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_MEETING, ResponseMessage.NOT_EXISTED_MEETING);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }
}
