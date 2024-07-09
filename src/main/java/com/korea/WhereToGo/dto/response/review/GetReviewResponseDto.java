package com.korea.WhereToGo.dto.response.review;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.ReviewEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetReviewResponseDto extends ResponseDto {
    private ReviewEntity review;

    private GetReviewResponseDto(ReviewEntity review) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.review = review;
    }

    public static ResponseEntity<? super GetReviewResponseDto> success(ReviewEntity review) {
        GetReviewResponseDto responseBody = new GetReviewResponseDto(review);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<? super ResponseDto> notExistReview() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_REVIEW, ResponseMessage.NOT_EXISTED_REVIEW);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }

}
