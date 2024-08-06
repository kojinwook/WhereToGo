package com.korea.WhereToGo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MeetingDto {
    private Long meetingId;
    private String title;
    private String creatorProfile;
    private String creatorNickname;
}
