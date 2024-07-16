package com.korea.WhereToGo.dto.request.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostTypingStatusRequestDto {
    private String roomId;
    private String sender;
    private boolean typing;
}
