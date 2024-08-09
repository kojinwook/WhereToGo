package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.MeetingEntity;
import com.korea.WhereToGo.entity.MeetingRequestEntity;
import com.korea.WhereToGo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingRequestRepository extends JpaRepository<MeetingRequestEntity, Long> {
    List<MeetingRequestEntity> findByMeeting_MeetingId(Long meetingId);
    boolean existsByMeetingAndUser(MeetingEntity meeting, UserEntity user);

    @Query("SELECT mr FROM meeting_request mr JOIN FETCH mr.user WHERE mr.meeting.meetingId = :meetingId")
    List<MeetingRequestEntity> findByMeeting_MeetingIdWithUser(@Param("meetingId") Long meetingId);

}
