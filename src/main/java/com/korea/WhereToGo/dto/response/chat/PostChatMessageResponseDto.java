package com.korea.WhereToGo.dto.response.chat;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PostChatMessageResponseDto extends ResponseDto {

    public PostChatMessageResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<? super PostChatMessageResponseDto> success(){
        PostChatMessageResponseDto responseBody = new PostChatMessageResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<? super ResponseDto> notExistChatRoom() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_CHAT_ROOM, ResponseMessage.NOT_EXISTED_CHAT_ROOM);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
