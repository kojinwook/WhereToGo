package com.korea.WhereToGo.dto.request.meeting;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostMeetingRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String introduction;
    @NotBlank
    private String content;
    @NotBlank
    private String nickname;

    private String imageUrl;
}
