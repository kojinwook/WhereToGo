package com.korea.WhereToGo.dto.response.Question;


import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.QuestionEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
public class GetQuestionResponseDto  extends ResponseDto {

    private Long questionId;
    private String title;
    private String content;
    private String userId;
    private String type;
    private String email;
    private Boolean answered;
    private LocalDateTime createDateTime;
    private LocalDateTime modifyDateTime;

    public GetQuestionResponseDto(QuestionEntity questionEntity){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.questionId = questionEntity.getQuestionId();
        this.title= questionEntity.getTitle();
        this.content=questionEntity.getContent();
        this.createDateTime=questionEntity.getCreateDateTime();
        this.modifyDateTime=questionEntity.getModifyDateTime();
        this.userId= questionEntity.getUserId();
        this.email= questionEntity.getEmail();
        this.type= questionEntity.getType();
        this.answered=questionEntity.getAnswered();
    }



    public static ResponseEntity<GetQuestionResponseDto> success(QuestionEntity questionEntity){
        GetQuestionResponseDto responseBody = new GetQuestionResponseDto(questionEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistQuestion(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_QUESTION,ResponseMessage.NOT_EXISTED_QUESTION);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }


}
