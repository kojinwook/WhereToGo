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
public class GetChatRoomResponseDto extends ResponseDto {

    List<ChatRoomEntity> chatRooms;

    public GetChatRoomResponseDto(List<ChatRoomEntity> chatRooms){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.chatRooms = chatRooms;
    }

    public static ResponseEntity<? super GetChatRoomResponseDto> success(List<ChatRoomEntity> chatRooms){
        GetChatRoomResponseDto response = new GetChatRoomResponseDto(chatRooms);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public static ResponseEntity<ResponseDto> notExistChatRoom(){
        ResponseDto response = new ResponseDto(ResponseCode.NOT_EXISTED_CHAT_ROOM, ResponseMessage.NOT_EXISTED_CHAT_ROOM);
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
    }
}
