package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.MeetingUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingUsersRepository extends JpaRepository<MeetingUsersEntity, Long> {
    List<MeetingUsersEntity> findByMeeting_MeetingId(Long meetingId);
}
