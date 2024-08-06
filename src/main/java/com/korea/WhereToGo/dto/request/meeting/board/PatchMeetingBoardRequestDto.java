package com.korea.WhereToGo.dto.request.meeting.board;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PatchMeetingBoardRequestDto {
    private String title;
    private String content;
    private String address;
    private List<String> imageList;
}
