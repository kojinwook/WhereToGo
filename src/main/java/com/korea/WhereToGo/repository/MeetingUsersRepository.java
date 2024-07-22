package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.MeetingUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeetingUsersRepository extends JpaRepository<MeetingUsersEntity, Long> {
    List<MeetingUsersEntity> findByMeeting_MeetingId(Long meetingId);
    List<MeetingUsersEntity> findByUser_UserId(String userId);
    Optional<MeetingUsersEntity> findByUserNickname(String userNickname);
}
