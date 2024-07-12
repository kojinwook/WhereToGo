package com.korea.WhereToGo.dto.response.Notice;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.Question.DeleteQuestionResponseDto;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.hibernate.sql.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class DeleteNoticeResponseDto extends ResponseDto {
    private DeleteNoticeResponseDto(){super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);}

    public static ResponseEntity<DeleteNoticeResponseDto> success(){
        DeleteNoticeResponseDto result = new DeleteNoticeResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> notExistNotice(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_NOTICE,ResponseMessage.NOT_EXISTED_NOTICE);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }




}
