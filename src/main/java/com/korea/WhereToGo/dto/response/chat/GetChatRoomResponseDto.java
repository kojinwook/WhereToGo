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

    List<ChatRoomEntity> chatRoomList;

    public GetChatRoomResponseDto(List<ChatRoomEntity> chatRoomList){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.chatRoomList = chatRoomList;
    }

    public static ResponseEntity<? super GetChatRoomResponseDto> success(List<ChatRoomEntity> chatRoomList){
        GetChatRoomResponseDto response = new GetChatRoomResponseDto(chatRoomList);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto response = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(response);
    }
}
