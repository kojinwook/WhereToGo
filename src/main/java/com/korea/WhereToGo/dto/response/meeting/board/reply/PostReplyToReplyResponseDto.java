package com.korea.WhereToGo.dto.response.meeting.board.reply;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PostReplyToReplyResponseDto extends ResponseDto {

    public PostReplyToReplyResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<? super PostReplyToReplyResponseDto> success(){
        PostReplyToReplyResponseDto responseBody = new PostReplyToReplyResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
