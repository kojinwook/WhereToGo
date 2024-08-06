package com.korea.WhereToGo.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@NoArgsConstructor
public class AdminSignUpRequestDto {
    @NotBlank
    private String userId;
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{8,13}$")
    private String password;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String nickname;
    @NotBlank
    private String phone;
    @NotBlank
    private String certificationNumber;
    @NotBlank
    private String secretKey;
}
