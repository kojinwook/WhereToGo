package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {
    List<ReviewEntity> findByContentId(String contentId);
    ReviewEntity findByReviewId(Long reviewId);
    List<ReviewEntity> findByNickname(String nickname);
}
