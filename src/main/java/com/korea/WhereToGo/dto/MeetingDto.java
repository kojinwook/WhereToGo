package com.korea.WhereToGo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MeetingDto {
    private Long meetingId;
    private String title;
    private String creatorProfile;
    private String creatorNickname;
}
