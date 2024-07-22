package com.korea.WhereToGo.dto.response.user;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.UserDto;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetUserListResponseDto extends ResponseDto {

    private List<UserDto> userList;

    public GetUserListResponseDto(List<UserDto> userList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userList = userList;
    }

    public static ResponseEntity<? super GetUserListResponseDto> success(List<UserDto> userList) {
        GetUserListResponseDto responseBody = new GetUserListResponseDto(userList);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistUser() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noPermission() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DO_NOT_HAVE_PERMISSION, ResponseMessage.DO_NOT_HAVE_PERMISSION);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseBody);
    }
}
