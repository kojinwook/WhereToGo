package com.korea.WhereToGo.dto.request.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WithdrawalUserRequestDto {
    private String userId;
    private String password;
}
