package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.MeetingBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingBoardRepository extends JpaRepository<MeetingBoardEntity, Long> {
    List<MeetingBoardEntity> findByMeeting_MeetingId(Long meetingId);
    MeetingBoardEntity findByMeetingBoardId(Long boardId);
}
