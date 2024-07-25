package com.korea.WhereToGo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.korea.WhereToGo.dto.request.auth.AdminSignUpRequestDto;
import com.korea.WhereToGo.dto.request.auth.SignUpRequestDto;
import com.korea.WhereToGo.dto.request.user.PatchUserRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="user")
@Table(name="user")
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private String userId;
    private String password;
    @Email
    @Column(name = "email")
    private String email;
    @Column(name = "nickname", unique = true)
    private String nickname;
    private String phoneNumber;
    @Column(name = "profile_image")
    private String profileImage;
    private String role;
    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime modifyDate;

    private List<String> likeBoardList;

    @Column(nullable = false)
    private double temperature;

    private boolean isBlocked = false;

    private LocalDate blockReleaseDate;

    private int reportCount = 0;

    private LocalDateTime lastMeetingCreated;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<FavoriteEntity> likes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<MeetingBoardEntity> meetingBoardList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<MeetingBoardReplyEntity> meetingBoardReply = new ArrayList<>();

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<MeetingEntity> meetings;

    public void increaseTemperature(double amount) {
        this.temperature += amount;
    }

    public void decreaseTemperature(double amount) {
        this.temperature -= amount;
        if (this.temperature < 0) {
            this.temperature = 0;
        }
    }

    public void updateLastMeetingCreated() {
        this.lastMeetingCreated = LocalDateTime.now();
    }

    public UserEntity(SignUpRequestDto dto) {
        this.userId = dto.getUserId();
        this.password = dto.getPassword();
        this.email = dto.getEmail();
        this.nickname = dto.getNickname();
        this.phoneNumber = dto.getPhone();
        this.role = "ROLE_USER";
        this.reportCount = 0;
    }

    public UserEntity(AdminSignUpRequestDto dto) {
        this.userId = dto.getUserId();
        this.password = dto.getPassword();
        this.email = dto.getEmail();
        this.nickname = dto.getNickname();
        this.phoneNumber = dto.getPhone();
        this.role = "ROLE_ADMIN";
    }

    public UserEntity(String userId, String email, String nickname, String phoneNumber, String profileImage) {
        this.userId = userId;
        this.password = "Password";
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
        this.nickname = nickname;
        this.role = "ROLE_USER";
    }

    public void patchUser(PatchUserRequestDto dto) {
        this.nickname = dto.getNickname();
        this.email = dto.getEmail();
        this.profileImage = dto.getProfileImage();
        this.phoneNumber = dto.getPhoneNumber();
    }
}