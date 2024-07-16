package com.korea.WhereToGo.dto.response.chat;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class UpdateReadStatusResponseDto extends ResponseDto {

    public UpdateReadStatusResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<? super UpdateReadStatusResponseDto> success(){
        UpdateReadStatusResponseDto responseBody = new UpdateReadStatusResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
