package com.korea.WhereToGo.dto.request.user;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
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
