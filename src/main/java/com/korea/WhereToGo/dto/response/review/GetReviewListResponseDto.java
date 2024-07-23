package com.korea.WhereToGo.dto.response.review;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.FestivalEntity;
import com.korea.WhereToGo.entity.ReviewEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetReviewListResponseDto extends ResponseDto {
    private List<ReviewEntity> reviews;
    private List<FestivalEntity> festivalList;

    public GetReviewListResponseDto(List<ReviewEntity> reviews, List<FestivalEntity> festivalList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.reviews = reviews;
        this.festivalList = festivalList;
    }

    public static ResponseEntity<? super GetReviewListResponseDto> success(List<ReviewEntity> reviews, List<FestivalEntity> festivalList) {
        GetReviewListResponseDto responseBody = new GetReviewListResponseDto(reviews, festivalList);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<? super ResponseDto> notExistReview() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_REVIEW, ResponseMessage.NOT_EXISTED_REVIEW);
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(responseBody);
    }
}
