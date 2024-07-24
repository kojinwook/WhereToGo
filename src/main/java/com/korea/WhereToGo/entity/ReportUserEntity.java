package com.korea.WhereToGo.entity;

import com.korea.WhereToGo.dto.request.user.PostReportUserRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
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
    private String reportContent;
    private String reportDate;

    public ReportUserEntity(PostReportUserRequestDto dto, String userNickname) {
        this.userNickname = userNickname;
        this.reportUserNickname = dto.getReportUserNickname();
        this.reportContent = dto.getReportContent();
        this.reportDate = LocalDateTime.now().toString();
    }
}
