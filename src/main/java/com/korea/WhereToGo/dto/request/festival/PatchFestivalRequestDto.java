package com.korea.WhereToGo.dto.request.festival;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PatchFestivalRequestDto {
    private String title;
    private String startDate;
    private String endDate;
    private String address1;
    private String firstImage;
    private String tel;
    private String mapX;
    private String mapY;
    private String modifyDate;
    private String areaCode;
    private String sigunguCode;
    private String contentId;
    private String contentTypeId;
    private String homepage;
}
