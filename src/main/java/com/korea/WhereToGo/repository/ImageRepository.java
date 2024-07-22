package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.ImageEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    List<ImageEntity> findByReviewReviewIdIn(List<Long> reviewIds);
    List<ImageEntity> findByReviewReviewId(Long reviewId);
    @EntityGraph(attributePaths = "question")
    List<ImageEntity> findByQuestion_QuestionId(Long questionId);
    @EntityGraph(attributePaths = "meeting")
    List<ImageEntity> findByMeeting_MeetingId(Long meetingId);
    List<ImageEntity> findByMeetingBoard_Meeting_MeetingId(Long meetingId);

    @Transactional
    void deleteByContentId(String contentId);
}
