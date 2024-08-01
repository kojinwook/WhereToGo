package com.korea.WhereToGo.dto.response.answer;


import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.AnswerEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetAllAnswerResponseDto extends ResponseDto {
    private List<AnswerEntity> answers;

    public GetAllAnswerResponseDto(List<AnswerEntity> answers){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.answers= answers;
    }
    public static ResponseEntity<GetAllAnswerResponseDto> success(List<AnswerEntity> answers){
        GetAllAnswerResponseDto responseBody = new GetAllAnswerResponseDto(answers);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
