package com.korea.WhereToGo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class MeetingUserDto {
    private String userId;
    private Long meetingUsersId;
    private Long meetingId;
    private String title;
    private String userNickname;
    private String userProfileImage;
    private LocalDateTime joinDate;

    public MeetingUserDto(String userId, String userNickname, String userProfileImage, LocalDateTime joinDate) {
        this.userId = userId;
        this.userNickname = userNickname;
        this.userProfileImage = userProfileImage;
        this.joinDate = joinDate;
    }
}

