package com.korea.WhereToGo.dto.request.question;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PatchQuestionRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String nickname;
    @NotBlank
    private String type;
    private List<String> imageList;
}
