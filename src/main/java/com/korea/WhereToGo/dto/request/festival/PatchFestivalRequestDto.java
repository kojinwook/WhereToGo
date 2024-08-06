package com.korea.WhereToGo.dto.request.festival;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class PatchFestivalRequestDto {
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String address1;
    private String firstImage;
    private String tel;
    private String contentId;
    private String homepage;
    private List<String> tags;
}
