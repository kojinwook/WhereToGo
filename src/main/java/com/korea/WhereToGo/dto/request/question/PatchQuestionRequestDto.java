package com.korea.WhereToGo.dto.request.question;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
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
