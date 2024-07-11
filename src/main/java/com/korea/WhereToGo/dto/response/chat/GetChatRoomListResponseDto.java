package com.korea.WhereToGo.dto.response.chat;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.ChatRoomEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetChatRoomListResponseDto extends ResponseDto {
    List<ChatRoomEntity> chatRoomList;

    public GetChatRoomListResponseDto(List<ChatRoomEntity> chatRoomList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.chatRoomList = chatRoomList;
    }

    public static ResponseEntity<? super GetChatRoomListResponseDto> success(List<ChatRoomEntity> chatRoomList) {
        GetChatRoomListResponseDto responseBody = new GetChatRoomListResponseDto(chatRoomList);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<? super ResponseDto> notExistChatRoom() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_CHAT_ROOM, ResponseMessage.NOT_EXISTED_CHAT_ROOM);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }
}
