package com.korea.WhereToGo.dto.response.Question;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class DeleteQuestionResponseDto extends ResponseDto {
    private DeleteQuestionResponseDto(){super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);}

    public static ResponseEntity<DeleteQuestionResponseDto> success(){
        DeleteQuestionResponseDto result = new DeleteQuestionResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    public static ResponseEntity<ResponseDto> notExistQuestion(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_FESTIVAL,ResponseMessage.NOT_EXISTED_FESTIVAL);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }





}
