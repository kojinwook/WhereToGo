package com.korea.WhereToGo.dto.response.chat;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.ChatMessageEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetSavedMessageResponseDto extends ResponseDto {
    private ChatMessageEntity savedMessage;

    public GetSavedMessageResponseDto(ChatMessageEntity savedMessage) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.savedMessage = savedMessage;
    }

    public static ResponseEntity<? super GetSavedMessageResponseDto> success(ChatMessageEntity savedMessage) {
        GetSavedMessageResponseDto responseBody = new GetSavedMessageResponseDto(savedMessage);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<? super ResponseDto> notExistChatMessage() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_CHAT_MESSAGE, ResponseMessage.NOT_EXISTED_CHAT_MESSAGE);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}

