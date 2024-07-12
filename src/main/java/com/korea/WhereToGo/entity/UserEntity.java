package com.korea.WhereToGo.entity;

import com.korea.WhereToGo.dto.request.auth.AdminSignUpRequestDto;
import com.korea.WhereToGo.dto.request.auth.SignUpRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
    private int id;
    @Column(name = "user_id")
    private String userId;
    private String password;
    @Email
    @Column(name = "email")
    private String email;
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

    public UserEntity(SignUpRequestDto dto) {
        this.userId = dto.getUserId();
        this.password = dto.getPassword();
        this.email = dto.getEmail();
        this.nickname = dto.getNickname();
        this.phoneNumber = dto.getPhoneNumber();
        this.role = "ROLE_USER";
        this.likeBoardList = new ArrayList<>();
    }

    public UserEntity(AdminSignUpRequestDto dto) {
        this.userId = dto.getUserId();
        this.password = dto.getPassword();
        this.email = dto.getEmail();
        this.nickname = dto.getNickname();
        this.phoneNumber = dto.getPhoneNumber();
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
}