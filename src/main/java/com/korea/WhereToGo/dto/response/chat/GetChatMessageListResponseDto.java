package com.korea.WhereToGo.dto.response.chat;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.ChatMessageEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetChatMessageListResponseDto extends ResponseDto {

    List<ChatMessageEntity> chatMessageList;

    public GetChatMessageListResponseDto(List<ChatMessageEntity> chatMessageList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.chatMessageList = chatMessageList;
    }

    public static ResponseEntity<? super GetChatMessageListResponseDto> success(List<ChatMessageEntity> chatMessageList) {
        GetChatMessageListResponseDto responseBody = new GetChatMessageListResponseDto(chatMessageList);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<? super ResponseDto> notExistChatRoom() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_CHAT_ROOM, ResponseMessage.NOT_EXISTED_CHAT_ROOM);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }
}
