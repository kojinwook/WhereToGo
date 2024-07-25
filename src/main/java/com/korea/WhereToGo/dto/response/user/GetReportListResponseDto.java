package com.korea.WhereToGo.dto.response.user;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.ReportUserEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetReportListResponseDto extends ResponseDto {

    private List<ReportUserEntity> reportList;

    public GetReportListResponseDto(List<ReportUserEntity> reportList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.reportList = reportList;
    }

    public static ResponseEntity<? super GetReportListResponseDto> success(List<ReportUserEntity> reportList) {
        GetReportListResponseDto responseBody = new GetReportListResponseDto(reportList);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noPermission() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DO_NOT_HAVE_PERMISSION, ResponseMessage.DO_NOT_HAVE_PERMISSION);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseBody);
    }
}
