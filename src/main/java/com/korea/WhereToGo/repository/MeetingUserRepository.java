package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.MeetingUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingUserRepository extends JpaRepository<MeetingUserEntity, Long> {
    boolean findByUserNickname(String nickname);
}
