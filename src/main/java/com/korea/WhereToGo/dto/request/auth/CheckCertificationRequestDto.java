package com.korea.WhereToGo.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckCertificationRequestDto {
    @NotBlank
    private String userId;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String certificationNumber;
}
