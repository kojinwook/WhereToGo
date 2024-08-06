package com.korea.WhereToGo.dto.request.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatchUserRequestDto {
    private String nickname;
    private String email;
    private String currentPassword;
    private String newPassword;
    private String profileImage;
    private String phoneNumber;
}
