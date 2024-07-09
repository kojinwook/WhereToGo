package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {
    List<ReviewEntity> findByContentId(String contentId);
    ReviewEntity findByReviewId(Long reviewId);
}
