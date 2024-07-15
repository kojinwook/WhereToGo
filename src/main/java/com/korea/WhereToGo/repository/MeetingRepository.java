package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.MeetingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends JpaRepository<MeetingEntity, Long> {
    MeetingEntity findByMeetingId(Long meetingId);
}
