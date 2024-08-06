package com.korea.WhereToGo.dto.request.answer;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostAnswerRequestDto {
    @NotBlank
    private String content;
    @NotBlank
    private String nickname;
    @NotNull
    private Long questionId;
}
