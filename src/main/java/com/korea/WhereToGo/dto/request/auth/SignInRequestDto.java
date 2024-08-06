package com.korea.WhereToGo.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignInRequestDto {
    @NotBlank
    private String userId;
    @NotBlank
    private String password;
}
