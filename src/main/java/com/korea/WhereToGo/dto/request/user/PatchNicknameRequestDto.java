package com.korea.WhereToGo.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatchNicknameRequestDto {
    @NotBlank
    private String nickname;
}
