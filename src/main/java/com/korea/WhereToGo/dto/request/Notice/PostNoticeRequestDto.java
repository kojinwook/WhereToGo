package com.korea.WhereToGo.dto.request.Notice;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostNoticeRequestDto {

    @NotBlank
    private String title;
    @NotBlank
    private String content;

    private String image;


}
