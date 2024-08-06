package com.korea.WhereToGo.dto.request.meeting;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PatchMeetingRequestDto {
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

    private List<String> categories;

    private List<String> locations;
}
