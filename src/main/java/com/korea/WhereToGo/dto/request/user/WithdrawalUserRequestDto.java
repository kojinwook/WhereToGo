package com.korea.WhereToGo.dto.request.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WithdrawalUserRequestDto {
    private String userId;
    private String password;
}
