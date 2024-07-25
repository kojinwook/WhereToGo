package com.korea.WhereToGo.dto.request.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostReportUserRequestDto {
    private String reportUserNickname;
    private String reportContent;
}
