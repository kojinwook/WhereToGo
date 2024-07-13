package com.korea.WhereToGo.dto.response.answer;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class DeleteAnswerResponseDto extends ResponseDto {
    private DeleteAnswerResponseDto(){super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);}

    public static ResponseEntity<DeleteAnswerResponseDto> success(){
        DeleteAnswerResponseDto result = new DeleteAnswerResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistedAnswer(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_ANSWER,ResponseMessage.NOT_EXISTED_ANSWER);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }




}
