package com.korea.WhereToGo.dto.response.user;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PatchNicknameResponseDto extends ResponseDto {
    private PatchNicknameResponseDto() {super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);}

    public static ResponseEntity<PatchNicknameResponseDto> success(){
        PatchNicknameResponseDto responseBody = new PatchNicknameResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> duplicateNickname(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATED_NICKNAME,ResponseMessage.DUPLICATED_NICKNAME);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBody);
    }
}
