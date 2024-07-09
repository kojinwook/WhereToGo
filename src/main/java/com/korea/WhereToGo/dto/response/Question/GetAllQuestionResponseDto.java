package com.korea.WhereToGo.dto.response.Question;


import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.QuestionEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetAllQuestionResponseDto extends ResponseDto {


    private List<QuestionEntity> questions;

    public GetAllQuestionResponseDto(List<QuestionEntity> questions){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.questions = questions;
    }
    public static ResponseEntity<GetAllQuestionResponseDto> success(List<QuestionEntity> questions){
        GetAllQuestionResponseDto responseBody = new GetAllQuestionResponseDto(questions);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }




}
