package com.korea.WhereToGo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageWithBoardIdDto {
    private Long imageId;
    private String image;
    private Long meetingBoardId;
}