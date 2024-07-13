package com.korea.WhereToGo.dto.response.answer;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.AnswerEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetAnswerResponseDto extends ResponseDto {
    private AnswerEntity answer;

    public GetAnswerResponseDto(AnswerEntity answerEntities){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.answer= answerEntities;
    }
    public static ResponseEntity<GetAnswerResponseDto> success(AnswerEntity answerEntities){
        GetAnswerResponseDto responseBody = new GetAnswerResponseDto(answerEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
    public static ResponseEntity<ResponseDto> notExistAnswer(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_ANSWER,ResponseMessage.NOT_EXISTED_ANSWER);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }


}
