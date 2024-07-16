package com.korea.WhereToGo.dto.response.meeting;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PostResponseToJoinResponseDto extends ResponseDto {

    public PostResponseToJoinResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<? super PostResponseToJoinResponseDto> success(){
        PostResponseToJoinResponseDto responseBody = new PostResponseToJoinResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistRequest(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_JOIN_REQUEST, ResponseMessage.NOT_EXISTED_JOIN_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
