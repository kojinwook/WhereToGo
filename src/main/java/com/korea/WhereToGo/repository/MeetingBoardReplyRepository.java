package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.MeetingBoardReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingBoardReplyRepository extends JpaRepository<MeetingBoardReplyEntity, Long> {
    List<MeetingBoardReplyEntity> findByMeetingBoardId(Long meetingBoardId);
    MeetingBoardReplyEntity findByReplyId(Long replyId);
}
