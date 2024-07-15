package com.korea.WhereToGo.dto.response.notice;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.NoticeEntity;
import lombok.Getter;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetSearchNoticeListResponseDto extends ResponseDto {
   private List<NoticeEntity> noticeList;

   public GetSearchNoticeListResponseDto(List<NoticeEntity> noticeList){
       super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
       this.noticeList = noticeList;
   }
   public static ResponseEntity<? super GetSearchNoticeListResponseDto> success(List<NoticeEntity> noticeList){
       GetSearchNoticeListResponseDto responseBody = new GetSearchNoticeListResponseDto(noticeList);
       return ResponseEntity.ok(responseBody);
   }
   public static ResponseEntity<ResponseDto> notExistNotice(){
       ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_NOTICE, ResponseMessage.NOT_EXISTED_NOTICE);
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
   }

}
