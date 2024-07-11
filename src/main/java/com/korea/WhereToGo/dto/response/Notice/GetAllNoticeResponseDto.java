package com.korea.WhereToGo.dto.response.Notice;


import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.NoticeEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetAllNoticeResponseDto extends ResponseDto {

    private List<NoticeEntity> notices;

    public GetAllNoticeResponseDto(List<NoticeEntity> notices){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.notices = notices;
    }

    public static ResponseEntity<GetAllNoticeResponseDto> success(List<NoticeEntity> notices){
        GetAllNoticeResponseDto responseBody = new GetAllNoticeResponseDto(notices);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
