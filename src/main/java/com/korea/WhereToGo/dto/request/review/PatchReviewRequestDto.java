package com.korea.WhereToGo.dto.request.review;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PatchReviewRequestDto {
    @NotBlank
    private String reviewId;
    @NotBlank
    private int rate;
    @NotBlank
    private String review;
    @NotBlank
    private String nickname;
    private List<String> imageList;
}
