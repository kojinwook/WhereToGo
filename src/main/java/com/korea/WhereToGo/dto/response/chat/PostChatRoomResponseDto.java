package com.korea.WhereToGo.dto.response.chat;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PostChatRoomResponseDto extends ResponseDto {
    private Long roomId;

    public PostChatRoomResponseDto(Long roomId) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.roomId = roomId;
    }

    public static ResponseEntity<? super PostChatRoomResponseDto> success(Long roomId) {
        PostChatRoomResponseDto responseBody = new PostChatRoomResponseDto(roomId);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<? super ResponseDto> alreadyExistChatRoom() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.ALREADY_EXIST_CHAT_ROOM, ResponseMessage.ALREADY_EXIST_CHAT_ROOM);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBody);
    }
}
