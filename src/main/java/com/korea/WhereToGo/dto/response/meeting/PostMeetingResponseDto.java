package com.korea.WhereToGo.dto.response.meeting;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PostMeetingResponseDto extends ResponseDto {

    private PostMeetingResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<? super PostMeetingResponseDto> success() {
        PostMeetingResponseDto responseBody = new PostMeetingResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> cannotCreateMeeting(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.CANNOT_CREATE_MEETING, ResponseMessage.CANNOT_CREATE_MEETING);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseBody);
    }
}
