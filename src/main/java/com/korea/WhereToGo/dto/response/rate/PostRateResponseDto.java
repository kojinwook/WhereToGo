package com.korea.WhereToGo.dto.response.rate;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PostRateResponseDto extends ResponseDto {

    private PostRateResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<? super PostRateResponseDto> success() {
        PostRateResponseDto responseBody = new PostRateResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<? super ResponseDto> notExistFestival() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_FESTIVAL, ResponseMessage.NOT_EXISTED_FESTIVAL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
