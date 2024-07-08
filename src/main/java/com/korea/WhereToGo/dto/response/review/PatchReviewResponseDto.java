package com.korea.WhereToGo.dto.response.review;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PatchReviewResponseDto extends ResponseDto {

    private PatchReviewResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<? super PatchReviewResponseDto> success() {
        PatchReviewResponseDto responseBody = new PatchReviewResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<? super ResponseDto> notExistReview() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_REVIEW, ResponseMessage.NOT_EXISTED_REVIEW);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<? super ResponseDto> notExistFestival() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_FESTIVAL, ResponseMessage.NOT_EXISTED_FESTIVAL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

}
