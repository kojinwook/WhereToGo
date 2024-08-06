package com.korea.WhereToGo.dto.request.review;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PatchReviewRequestDto {
    @NotBlank
    private Long reviewId;
    @NotBlank
    private int rate;
    @NotBlank
    private String review;
    @NotBlank
    private String nickname;
    private List<String> imageList;
}
