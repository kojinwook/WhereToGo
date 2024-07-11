package com.korea.WhereToGo.dto.response.Answer;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.AnswerEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }


}
