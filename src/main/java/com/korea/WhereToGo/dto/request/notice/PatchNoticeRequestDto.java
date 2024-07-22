package com.korea.WhereToGo.dto.request.notice;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PatchNoticeRequestDto {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String nickname;

    private List<String> imageList;




}
