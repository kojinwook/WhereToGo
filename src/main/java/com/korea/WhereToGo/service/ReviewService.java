package com.korea.WhereToGo.service;

import com.korea.WhereToGo.dto.request.review.PatchReviewRequestDto;
import com.korea.WhereToGo.dto.request.review.PostReviewRequestDto;
import com.korea.WhereToGo.dto.response.review.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    ResponseEntity<? super PostReviewResponseDto> postRate(PostReviewRequestDto dto, String userId);
    ResponseEntity<? super GetAverageRateResponseDto> getRateAverage(List<String> contentIds);
    ResponseEntity<? super GetReviewResponseDto> getReview(Long reviewId);
    ResponseEntity<? super PatchReviewResponseDto> patchReview(PatchReviewRequestDto dto, String userId);
    ResponseEntity<? super GetReviewListResponseDto> getReviewList(String contentId);
}
