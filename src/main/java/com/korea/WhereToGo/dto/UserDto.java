package com.korea.WhereToGo.dto;

import com.korea.WhereToGo.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDto {
    private String userId;
    private String nickname;
    private String email;
    private String profileImage;
    private LocalDateTime createDate;

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
    }
}
