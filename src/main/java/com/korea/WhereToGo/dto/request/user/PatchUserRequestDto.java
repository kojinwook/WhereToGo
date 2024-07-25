package com.korea.WhereToGo.dto.request.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchUserRequestDto {
    private String nickname;
    private String email;
    private String currentPassword;
    private String newPassword;
    private String profileImage;
    private String phoneNumber;
}
