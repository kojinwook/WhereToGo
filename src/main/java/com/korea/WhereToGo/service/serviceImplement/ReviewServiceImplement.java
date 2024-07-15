package com.korea.WhereToGo.service.serviceImplement;

import com.korea.WhereToGo.dto.request.review.PatchReviewRequestDto;
import com.korea.WhereToGo.dto.request.review.PostReviewRequestDto;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.dto.response.review.*;
import com.korea.WhereToGo.entity.FestivalEntity;
import com.korea.WhereToGo.entity.ImageEntity;
import com.korea.WhereToGo.entity.MeetingEntity;
import com.korea.WhereToGo.entity.ReviewEntity;
import com.korea.WhereToGo.repository.FestivalRepository;
import com.korea.WhereToGo.repository.ImageRepository;
import com.korea.WhereToGo.repository.ReviewRepository;
import com.korea.WhereToGo.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImplement implements ReviewService {

    private final FestivalRepository festivalRepository;
    private final ImageRepository imageRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public ResponseEntity<? super PostReviewResponseDto> postReview(PostReviewRequestDto dto, String userId) {
        String contentId = dto.getContentId();
        try {
            FestivalEntity festivalEntity = festivalRepository.findByContentId(contentId);
            if (festivalEntity == null) return PostReviewResponseDto.notExistFestival();

            ReviewEntity reviewEntity = new ReviewEntity(dto, contentId);
            reviewRepository.save(reviewEntity);

            List<String> imageList = dto.getImageList();
            List<ImageEntity> imageEntities = new ArrayList<>();

            for (String image : imageList) {
                ImageEntity imageEntity = new ImageEntity(contentId, image, userId);
                imageEntity.setReview(reviewEntity);
                imageEntities.add(imageEntity);
            }
            imageRepository.saveAll(imageEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostReviewResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetAverageRateResponseDto> getRateAverage(List<String> contentIds) {
        Map<String, Double> averages = contentIds.stream()
                .collect(Collectors.toMap(
                        contentId -> contentId,
                        contentId -> {
                            List<ReviewEntity> rates = reviewRepository.findByContentId(contentId);
                            return rates.stream()
                                    .mapToInt(ReviewEntity::getRate)
                                    .average()
                                    .orElse(0.0);
                        }
                ));

        return GetAverageRateResponseDto.success(averages);
    }

    @Override
    public ResponseEntity<? super GetReviewResponseDto> getReview(Long reviewId) {
        ReviewEntity reviewEntity = reviewRepository.findByReviewId(reviewId);
        if (reviewEntity == null) {
            return GetReviewResponseDto.notExistReview();
        }

        List<ImageEntity> imageEntities = imageRepository.findByReviewReviewId(reviewId);
        reviewEntity.setImages(imageEntities);

        return GetReviewResponseDto.success(reviewEntity);
    }

    @Override
    public ResponseEntity<? super PatchReviewResponseDto> patchReview(PatchReviewRequestDto dto, String userId) {
        String reviewId = dto.getReviewId();
        try {

//            UserEntity userEntity = userRepository.findByUserId(userId);
//            if (userEntity == null) return PatchReviewResponseDto.notExistUser();

            ReviewEntity reviewEntity = new ReviewEntity(dto);
            reviewRepository.save(reviewEntity);

            List<String> imageList = dto.getImageList();
            List<ImageEntity> imageEntities = new ArrayList<>();

            for (String image : imageList) {
                ImageEntity imageEntity = new ImageEntity(reviewId, image, userId);
                imageEntities.add(imageEntity);
            }
            imageRepository.saveAll(imageEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchReviewResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetReviewListResponseDto> getReviewList(String userId) {
        List<ReviewEntity> reviews = new ArrayList<>();
        try {
            reviews = reviewRepository.findByUserId(userId);
            if (reviews.isEmpty()) return GetReviewListResponseDto.notExistReview();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetReviewListResponseDto.success(reviews);
    }

    @Override
    public ResponseEntity<? super GetAllReviewResponseDto> getAllReview(String contentId) {
        List<ReviewEntity> reviews = reviewRepository.findByContentId(contentId);
        if (reviews.isEmpty()) {
            return GetAllReviewResponseDto.notExistReview();
        }

        List<Long> reviewIds = reviews.stream().map(ReviewEntity::getReviewId).collect(Collectors.toList());
        List<ImageEntity> imageEntities = imageRepository.findByReviewReviewIdIn(reviewIds);

        for (ReviewEntity review : reviews) {
            List<ImageEntity> relatedImages = imageEntities.stream()
                    .filter(image -> image.getReview().getReviewId().equals(review.getReviewId()))
                    .collect(Collectors.toList());
            review.setImages(relatedImages);
        }

        return GetAllReviewResponseDto.success(reviews);
    }
}
