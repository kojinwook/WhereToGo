package com.korea.WhereToGo.dto.request.meeting;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostMeetingRequestDto {
    @NotBlank(message = "모임 명은 필수입니다.")
    private String title;

    @NotBlank(message = "한 줄 소개는 필수입니다.")
    private String introduction;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    private String meetingImage;
}
