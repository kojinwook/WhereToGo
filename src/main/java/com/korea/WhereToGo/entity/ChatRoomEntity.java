package com.korea.WhereToGo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chat_room")
public class ChatRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    @Column(name = "room_name", nullable = false)
    private String roomName;
    @Column(name = "user_id", nullable = false)
    private String userId;
    @Column(name = "nickname", nullable = false)
    private String nickname;
    @Column(name = "creator_nickname", nullable = false)
    private String creatorNickname;
}
