package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.MeetingBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingBoardRepository extends JpaRepository<MeetingBoardEntity, Long> {
}
