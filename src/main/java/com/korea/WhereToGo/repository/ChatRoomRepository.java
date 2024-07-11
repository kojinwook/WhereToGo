package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Long> {
    ChatRoomEntity findByRoomId(Long roomId);
    ChatRoomEntity findByRoomName(String roomName);
    List<ChatRoomEntity> findByNickname(String Nickname);
}
