package com.korea.WhereToGo.dto.response.user;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class UnBlockUserResponseDto extends ResponseDto {

    public UnBlockUserResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<? super UnBlockUserResponseDto> success(){
        UnBlockUserResponseDto responseBody = new UnBlockUserResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noPermission(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.DO_NOT_HAVE_PERMISSION, ResponseMessage.DO_NOT_HAVE_PERMISSION);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseBody);
    }
}
