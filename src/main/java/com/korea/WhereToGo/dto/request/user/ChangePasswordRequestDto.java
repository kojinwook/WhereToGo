package com.korea.WhereToGo.dto.request.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangePasswordRequestDto {
    private String userId;
    private String currentPassword;
    private String newPassword;
}
