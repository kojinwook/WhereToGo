package com.korea.WhereToGo.dto.request.rate;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostRateRequestDto {
    @NotBlank
    private String contentId;
    @NotBlank
    private int rate;
    @NotBlank
    private String review;
}
