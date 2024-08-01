package com.korea.WhereToGo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.korea.WhereToGo.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "meeting_users")
@Table(name = "meeting_users")
public class MeetingUsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meetingUsersId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    @JsonIgnore
    private MeetingEntity meeting;

    @Transient
    private UserDto userDto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_user_id")
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
