package com.korea.WhereToGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
public class MeetingUserDto {
    private Long userId;
    private Long meetingUsersId;
    private Long meetingId;
    private String title;
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

