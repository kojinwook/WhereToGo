package com.korea.WhereToGo.entity;

import com.korea.WhereToGo.dto.request.user.PostReportUserRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "report_user")
@Table(name = "report_user")
public class ReportUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    private String userNickname;
    private String reportUserNickname;
    private String reportType;
    private String incidentDescription;
    private String incidentTimeDate;
    private String incidentLocation;
    private String impactDescription;
    private String reportDate;

    @OneToMany(mappedBy = "reportUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ImageEntity> imageList;

    public ReportUserEntity(PostReportUserRequestDto dto, String userNickname) {
        this.userNickname = userNickname;
        this.reportUserNickname = dto.getReportUserNickname();
        this.reportType = dto.getReportType();
        this.incidentDescription = dto.getIncidentDescription();
        this.incidentTimeDate = dto.getIncidentTimeDate();
        this.incidentLocation = dto.getIncidentLocation();
        this.impactDescription = dto.getImpactDescription();
        this.reportDate = LocalDateTime.now().toString();
    }
}
