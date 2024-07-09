package com.korea.WhereToGo.dto.request.review;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostReviewRequestDto {
    @NotBlank
    private String contentId;
    @NotBlank
    private int rate;
    @NotBlank
    private String review;
    private List<String> imageList;
}
