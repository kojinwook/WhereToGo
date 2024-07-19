package com.korea.WhereToGo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class MeetingUserDto {
    private Long userId;
    private String userNickname;
    private String userProfileImage;
    private LocalDateTime joinDate;

    public MeetingUserDto(Long userId, String userNickname, String userProfileImage, LocalDateTime joinDate) {
        this.userId = userId;
        this.userNickname = userNickname;
        this.userProfileImage = userProfileImage;
        this.joinDate = joinDate;
    }

}

