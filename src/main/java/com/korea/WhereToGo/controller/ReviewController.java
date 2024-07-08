package com.korea.WhereToGo.controller;

import com.korea.WhereToGo.dto.request.review.PatchReviewRequestDto;
import com.korea.WhereToGo.dto.request.review.PostReviewRequestDto;
import com.korea.WhereToGo.dto.response.review.*;
import com.korea.WhereToGo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/postReview")
    public ResponseEntity<? super PostReviewResponseDto> saveRate(
            @RequestBody PostReviewRequestDto dto,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PostReviewResponseDto> response = reviewService.postRate(dto, userId);
        return response;
    }

    @GetMapping("/getAverageRate")
    public ResponseEntity<? super GetAverageRateResponseDto> getRateAverage(
            @RequestParam List<String> contentId
    ) {
        ResponseEntity<? super GetAverageRateResponseDto> response = reviewService.getRateAverage(contentId);
        return response;
    }

    @GetMapping("/getReview")
    public ResponseEntity<? super GetReviewResponseDto> getReview(
            @RequestParam Long reviewId
    ) {
        ResponseEntity<? super GetReviewResponseDto> response = reviewService.getReview(reviewId);
        return response;
    }

    @PatchMapping("/patchReview")
    public ResponseEntity<? super PatchReviewResponseDto> patchReview(
            @RequestBody PatchReviewRequestDto dto,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PatchReviewResponseDto> response = reviewService.patchReview(dto, userId);
        return response;
    }

    @GetMapping("/getReviewList")
    public ResponseEntity<? super GetReviewListResponseDto> getReviewList(
            @RequestParam String contentId
    ) {
        ResponseEntity<? super GetReviewListResponseDto> response = reviewService.getReviewList(contentId);
        return response;
    }
}
