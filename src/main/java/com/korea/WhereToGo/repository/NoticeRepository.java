package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {
    NoticeEntity findByNoticeId(Long NoticeId);
    List<NoticeEntity> findByTitleContainingOrContentContaining(String titleKeyword, String contentKeyword);
}
