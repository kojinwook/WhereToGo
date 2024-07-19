package com.korea.WhereToGo.dto.request.meeting.board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PatchMeetingBoardRequestDto {
    private String title;
    private String content;
    private String address;
    private List<String> imageList;
}
