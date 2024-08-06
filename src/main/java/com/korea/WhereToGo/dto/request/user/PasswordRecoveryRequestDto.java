package com.korea.WhereToGo.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PasswordRecoveryRequestDto {
    @NotBlank
    @Email
    private String email;

}
