package com.korea.WhereToGo.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailCertificationRequestDto {
    @NotBlank
    private String userId;
    @NotBlank
    @Email
    private String email;
}
