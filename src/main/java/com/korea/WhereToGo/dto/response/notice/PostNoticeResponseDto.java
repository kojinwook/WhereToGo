package com.korea.WhereToGo.dto.response.notice;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PostNoticeResponseDto extends ResponseDto {
    private PostNoticeResponseDto(){super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);}

    public static ResponseEntity<PostNoticeResponseDto> success(){
        PostNoticeResponseDto responseBody = new PostNoticeResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }


}
