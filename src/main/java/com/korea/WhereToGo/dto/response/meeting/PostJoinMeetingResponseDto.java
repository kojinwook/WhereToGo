package com.korea.WhereToGo.dto.response.meeting;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PostJoinMeetingResponseDto extends ResponseDto {

    public PostJoinMeetingResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<? super PostJoinMeetingResponseDto> success(){
        PostJoinMeetingResponseDto responseBody = new PostJoinMeetingResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistMeeting(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_MEETING, ResponseMessage.NOT_EXISTED_MEETING);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> alreadyRequested(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.ALREADY_REQUESTED, ResponseMessage.ALREADY_REQUESTED);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
