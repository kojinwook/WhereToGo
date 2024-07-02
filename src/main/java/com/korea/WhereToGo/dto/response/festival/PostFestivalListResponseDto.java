package com.korea.WhereToGo.dto.response.festival;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PostFestivalListResponseDto extends ResponseDto {

    public PostFestivalListResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<PostFestivalListResponseDto> success(){
        PostFestivalListResponseDto responseBody = new PostFestivalListResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> duplicate(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.DUPLICATE_FESTIVAL, ResponseMessage.DUPLICATE_FESTIVAL);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBody);
    }
}
