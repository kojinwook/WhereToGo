package com.korea.WhereToGo.dto.response.question;


import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.QuestionEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetQuestionResponseDto  extends ResponseDto {

    private QuestionEntity question;

    public GetQuestionResponseDto(QuestionEntity question){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.question = question;
    }

    public static ResponseEntity<GetQuestionResponseDto> success(QuestionEntity question){
        GetQuestionResponseDto responseBody = new GetQuestionResponseDto(question);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistQuestion(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_QUESTION,ResponseMessage.NOT_EXISTED_QUESTION);
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(responseBody);
    }
}
