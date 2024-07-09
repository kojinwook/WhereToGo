package com.korea.WhereToGo.dto.response.Question;


import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PatchQuestionResponseDto extends ResponseDto {

    private PatchQuestionResponseDto(){super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);}

    public static ResponseEntity<PatchQuestionResponseDto> success(){
        PatchQuestionResponseDto responseBody = new PatchQuestionResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistQuestion(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_QUESTION,ResponseMessage.NOT_EXISTED_QUESTION);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }




}
