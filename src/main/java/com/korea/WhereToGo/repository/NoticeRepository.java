package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {
    NoticeEntity findByNoticeId(Long NoticeId);
}
