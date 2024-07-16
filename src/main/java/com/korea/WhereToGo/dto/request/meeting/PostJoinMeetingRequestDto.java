package com.korea.WhereToGo.dto.request.meeting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostJoinMeetingRequestDto {
    private Long meetingId;
    private String nickname;
}
