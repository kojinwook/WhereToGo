package com.korea.WhereToGo.dto.request.chat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostTypingStatusRequestDto {
    private String roomId;
    private String sender;
    private boolean typing;
}
