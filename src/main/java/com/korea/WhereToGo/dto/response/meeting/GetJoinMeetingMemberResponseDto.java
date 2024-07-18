package com.korea.WhereToGo.dto.response.meeting;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.MeetingUserDto;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetJoinMeetingMemberResponseDto extends ResponseDto {
    List<MeetingUserDto> meetingUsersList;

    public GetJoinMeetingMemberResponseDto(List<MeetingUserDto> meetingUsersList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.meetingUsersList = meetingUsersList;
    }

    public static ResponseEntity<? super GetJoinMeetingMemberResponseDto> success(List<MeetingUserDto> meetingUsersList) {
        GetJoinMeetingMemberResponseDto responseBody = new GetJoinMeetingMemberResponseDto(meetingUsersList);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistUser() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistMeeting() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_MEETING, ResponseMessage.NOT_EXISTED_MEETING);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
