package com.korea.WhereToGo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImageWithBoardIdDto {
    private Long imageId;
    private String image;
    private Long meetingBoardId;
}