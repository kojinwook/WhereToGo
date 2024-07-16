package com.korea.WhereToGo.dto.request.meeting;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    private List<String> imageList;

    private int maxParticipants;

    private List<String> tags;

    private List<String> areas;
}
