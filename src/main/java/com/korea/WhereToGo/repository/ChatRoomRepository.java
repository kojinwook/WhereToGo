package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Long> {
    ChatRoomEntity findByRoomId(Long roomId);
    Optional<ChatRoomEntity> findByNicknameAndCreatorNickname(String nickname, String creatorNickname);
    List<ChatRoomEntity> findByNicknameOrCreatorNickname(String nickname, String creatorNickname);
}
