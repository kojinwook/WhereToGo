package com.korea.WhereToGo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.korea.WhereToGo.dto.UserDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "meeting_users")
@Table(name = "meeting_users", indexes = {
        @Index(name = "idx_meeting_id", columnList = "meeting_id"),
        @Index(name = "idx_user_id", columnList = "user_id"),
        @Index(name = "idx_target_user_id", columnList = "target_user_id")
})
public class MeetingUsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meetingUsersId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id", nullable = false)
    @JsonIgnore
    private MeetingEntity meeting;

    @Transient
    private UserDto userDto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_user_id", nullable = false)
    @JsonBackReference
    private UserEntity targetUser;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "user_profile_image")
    private String userProfileImage;

    @Column(name = "join_date")
    private LocalDateTime joinDate;

    @Column(name = "liked")
    private boolean liked;

    @Column(name = "is_participant")
    private boolean isParticipant;
}
