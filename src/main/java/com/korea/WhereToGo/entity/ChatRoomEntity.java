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
    @Column(name = "room_name", nullable = false, unique = true)
    private String roomName;
    @Column(name = "nickname", nullable = false)
    private String nickname;
    @Column(name = "creator_nickname", nullable = false)
    private String creatorNickname;
    @Column(name = "creator_profile_image")
    private String creatorProfileImage;
}
