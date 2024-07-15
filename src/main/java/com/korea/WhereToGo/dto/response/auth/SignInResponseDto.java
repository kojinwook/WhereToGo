package com.korea.WhereToGo.dto.response.auth;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SignInResponseDto extends ResponseDto {

    private  String token;
    private int expirationTime;

    private SignInResponseDto(String token, int expirationTime) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.token = token;
        this.expirationTime = expirationTime;
    }

    public static ResponseEntity<SignInResponseDto> success(String token, boolean infinite) {
        int expirationTime = infinite ? -1 : 3600;
        SignInResponseDto responseBody = new SignInResponseDto(token, expirationTime);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> SignInFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }


}
