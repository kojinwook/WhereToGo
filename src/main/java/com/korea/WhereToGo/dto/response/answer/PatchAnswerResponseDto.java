package com.korea.WhereToGo.dto.response.answer;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PatchAnswerResponseDto extends ResponseDto {
    private PatchAnswerResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<PatchAnswerResponseDto> success(){
        PatchAnswerResponseDto responseBody = new PatchAnswerResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistAnswer(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_ANSWER,ResponseMessage.NOT_EXISTED_ANSWER);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }
}
