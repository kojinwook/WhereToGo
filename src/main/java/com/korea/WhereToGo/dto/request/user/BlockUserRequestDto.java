package com.korea.WhereToGo.dto.request.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BlockUserRequestDto {
    private String userId;
    private int blockDays;
}
