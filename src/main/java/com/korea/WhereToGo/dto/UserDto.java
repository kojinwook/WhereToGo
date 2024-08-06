package com.korea.WhereToGo.dto;

import com.korea.WhereToGo.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserDto {
    private String userId;
    private String nickname;
    private String email;
    private String profileImage;
    private LocalDateTime createDate;
    private double temperature;
    private int reportCount;
    private boolean isBlocked;

    public UserDto(String userId, String nickname, String profileImage) {
        this.userId = userId;
        this.nickname = nickname;
        this.profileImage = profileImage;
    }

    public UserDto(UserEntity user) {
        this.userId = user.getUserId();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.profileImage = user.getProfileImage();
        this.createDate = user.getCreateDate();
        this.temperature = user.getTemperature();
        this.reportCount = user.getReportCount();
        this.isBlocked = user.isBlocked();
    }
}
