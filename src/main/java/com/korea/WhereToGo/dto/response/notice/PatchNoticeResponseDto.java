package com.korea.WhereToGo.dto.response.notice;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PatchNoticeResponseDto extends ResponseDto {
    private PatchNoticeResponseDto(){super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);}

    public static ResponseEntity<PatchNoticeResponseDto> success(){
        PatchNoticeResponseDto responseBody = new PatchNoticeResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    public static ResponseEntity<ResponseDto> notExistNotice(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_NOTICE,ResponseMessage.NOT_EXISTED_NOTICE);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
