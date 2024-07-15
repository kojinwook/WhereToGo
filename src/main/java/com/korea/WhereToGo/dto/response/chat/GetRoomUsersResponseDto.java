package com.korea.WhereToGo.dto.response.chat;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.UserDto;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetRoomUsersResponseDto extends ResponseDto {

    private List<UserDto> users;

    public GetRoomUsersResponseDto(List<UserDto> users) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.users = users;
    }

    public static ResponseEntity<? super GetRoomUsersResponseDto> success(List<UserDto> users) {
        GetRoomUsersResponseDto responseBody = new GetRoomUsersResponseDto(users);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistChatRoom() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_CHAT_ROOM, ResponseMessage.NOT_EXISTED_CHAT_ROOM);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
