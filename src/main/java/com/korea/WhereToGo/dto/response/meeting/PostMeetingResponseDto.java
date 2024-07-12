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

    public static ResponseEntity<PostMeetingResponseDto> success() {
        PostMeetingResponseDto responseBody = new PostMeetingResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
