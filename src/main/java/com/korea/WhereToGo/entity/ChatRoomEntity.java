package com.korea.WhereToGo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chat_room")
public class ChatRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "room_name", nullable = false)
    private String roomName;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "creator_nickname", nullable = false)
    private String creatorNickname;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id",insertable = false, updatable = false)
    @JsonBackReference
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "creator_nickname",  referencedColumnName = "nickname", insertable = false, updatable = false)
    @JsonBackReference
    private UserEntity creator;
}
