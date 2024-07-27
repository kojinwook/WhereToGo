package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.ChatMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity, Long> {
    List<ChatMessageEntity> findByRoomId(Long roomId);
    ChatMessageEntity findByMessageId(Long messageId);
    ChatMessageEntity findByMessageKey(String messageKey);
    List<ChatMessageEntity> findBySender(String nickname);
    @Query("SELECT m FROM ChatMessageEntity m WHERE m.roomId = :roomId ORDER BY m.timestamp DESC LIMIT 1")
    ChatMessageEntity findLastMessageByRoomId(Long roomId);
}
