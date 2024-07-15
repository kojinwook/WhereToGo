package com.korea.WhereToGo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String userId;
    private String nickname;
    private String profileImage;

    public UserDto(String userId, String nickname, String profileImage) {
        this.userId = userId;
        this.nickname = nickname;
        this.profileImage = profileImage;
    }
}
