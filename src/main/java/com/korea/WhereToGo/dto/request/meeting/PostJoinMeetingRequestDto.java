package com.korea.WhereToGo.dto.request.meeting;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostJoinMeetingRequestDto {
    private Long meetingId;
    private String nickname;
}
