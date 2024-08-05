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
public class GetTop3TemperatureUserResponseDto extends ResponseDto {

    private List<UserDto> userList;

    public GetTop3TemperatureUserResponseDto(List<UserDto> userList){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userList = userList;
    }

    public static ResponseEntity<? super GetTop3TemperatureUserResponseDto> success(List<UserDto> userList){
        GetTop3TemperatureUserResponseDto responseBody = new GetTop3TemperatureUserResponseDto(userList);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
