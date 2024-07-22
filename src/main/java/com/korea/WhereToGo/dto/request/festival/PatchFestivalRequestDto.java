package com.korea.WhereToGo.dto.request.festival;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PatchFestivalRequestDto {
    private String title;
    private String startDate;
    private LocalDate endDate;
    private String address1;
    private String firstImage;
    private String tel;
    private String contentId;
    private String homepage;
    private List<String> tags;
}
