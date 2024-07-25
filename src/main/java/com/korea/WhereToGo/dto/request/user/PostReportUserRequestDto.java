package com.korea.WhereToGo.dto.request.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostReportUserRequestDto {
    private String reportUserNickname;
    private String reportType;
    private String incidentDescription;
    private String incidentTimeDate;
    private String incidentLocation;
    private String impactDescription;
    private List<String> imageList;
}
