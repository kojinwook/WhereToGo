package com.korea.WhereToGo.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NicknameCheckRequestDto {
    @NotBlank
    private String nickname;
}
