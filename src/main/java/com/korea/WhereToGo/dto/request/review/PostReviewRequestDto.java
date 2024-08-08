package com.korea.WhereToGo.dto.request.review;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PostReviewRequestDto {
    @NotBlank
    private String contentId;
    @NotBlank
    private int rate;
    @NotBlank
    private String review;
    @NotBlank
    private String nickname;
    private String profileImage;
    private List<String> imageList;
}
