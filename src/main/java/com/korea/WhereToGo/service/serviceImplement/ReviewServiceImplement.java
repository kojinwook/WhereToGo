package com.korea.WhereToGo.service.serviceImplement;

import com.korea.WhereToGo.dto.request.review.PatchReviewRequestDto;
import com.korea.WhereToGo.dto.request.review.PostReviewRequestDto;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.dto.response.review.*;
import com.korea.WhereToGo.entity.FestivalEntity;
import com.korea.WhereToGo.entity.ImageEntity;
import com.korea.WhereToGo.entity.ReviewEntity;
import com.korea.WhereToGo.entity.UserEntity;
import com.korea.WhereToGo.repository.FestivalRepository;
import com.korea.WhereToGo.repository.ImageRepository;
import com.korea.WhereToGo.repository.ReviewRepository;
import com.korea.WhereToGo.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super PostReviewResponseDto> postReview(PostReviewRequestDto dto, String userId) {
        String contentId = dto.getContentId();
        try {

            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return PostReviewResponseDto.notExistUser();

            userEntity.increaseTemperature(0.5);
            if (dto.getRate() >= 4) userEntity.increaseTemperature(0.5);
            userRepository.save(userEntity);

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
        ReviewEntity reviewEntity = new ReviewEntity();
        try {
            reviewEntity = reviewRepository.findByReviewId(reviewId);
            if (reviewEntity == null) {
                return GetReviewResponseDto.notExistReview();
            }

            List<ImageEntity> imageEntities = imageRepository.findByReviewReviewId(reviewId);
            reviewEntity.setImageList(imageEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetReviewResponseDto.success(reviewEntity);
    }

    @Override
    public ResponseEntity<? super PatchReviewResponseDto> patchReview(PatchReviewRequestDto dto, String userId) {
        Long reviewId = dto.getReviewId();
        try {
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) return PatchReviewResponseDto.notExistUser();

            ReviewEntity reviewEntity = reviewRepository.findByReviewId(reviewId);
            if (reviewEntity == null) return PatchReviewResponseDto.notExistReview();

            reviewEntity.PatchReview(dto);
            reviewRepository.save(reviewEntity);

            List<String> newImageList = dto.getImageList();

            List<ImageEntity> existingImages = reviewEntity.getImageList();

            List<ImageEntity> imageEntities = new ArrayList<>();

            for (ImageEntity existingImage : existingImages) {
                imageRepository.delete(existingImage);
            }

            for (String imageUrl : newImageList) {
                ImageEntity imageEntity = new ImageEntity(imageUrl, reviewEntity, userId);
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
        List<FestivalEntity> festivalList = new ArrayList<>();
        try {
            reviews = reviewRepository.findByNickname(userId);
            if (reviews.isEmpty()) return GetReviewListResponseDto.notExistReview();

            List<String> contentIds = reviews.stream().map(ReviewEntity::getContentId).toList();
            festivalList = festivalRepository.findByContentIdIn(contentIds.stream().map(Object::toString).collect(Collectors.toList()));

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetReviewListResponseDto.success(reviews, festivalList);
    }

    @Override
    public ResponseEntity<? super GetAllReviewResponseDto> getAllReview(String contentId) {
        List<ReviewEntity> reviews = reviewRepository.findByContentId(contentId);

        List<Long> reviewIds = reviews.stream().map(ReviewEntity::getReviewId).collect(Collectors.toList());
        List<ImageEntity> imageEntities = imageRepository.findByReviewReviewIdIn(reviewIds);

        for (ReviewEntity review : reviews) {
            List<ImageEntity> relatedImages = imageEntities.stream()
                    .filter(image -> image.getReview().getReviewId().equals(review.getReviewId()))
                    .collect(Collectors.toList());
            review.setImageList(relatedImages);
        }

        return GetAllReviewResponseDto.success(reviews);
    }
}
