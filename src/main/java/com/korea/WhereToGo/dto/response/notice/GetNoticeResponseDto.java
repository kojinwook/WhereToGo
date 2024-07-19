package com.korea.WhereToGo.dto.response.notice;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.NoticeEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
public class GetNoticeResponseDto extends ResponseDto {
    private NoticeEntity notice;

    public GetNoticeResponseDto(NoticeEntity notice){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.notice = notice;
    }

    public static ResponseEntity<GetNoticeResponseDto> success(NoticeEntity noticeEntity){
        GetNoticeResponseDto responseBody = new GetNoticeResponseDto(noticeEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }


    public static ResponseEntity<ResponseDto> notExistNotice(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_NOTICE,ResponseMessage.NOT_EXISTED_NOTICE);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }





}
