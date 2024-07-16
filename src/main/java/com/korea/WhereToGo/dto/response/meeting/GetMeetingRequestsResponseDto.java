package com.korea.WhereToGo.dto.response.meeting;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.MeetingRequestEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetMeetingRequestsResponseDto extends ResponseDto {
    private List<MeetingRequestEntity> requests;

    private GetMeetingRequestsResponseDto(List<MeetingRequestEntity> requests) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.requests = requests;
    }

    public static ResponseEntity<? super GetMeetingRequestsResponseDto> success(List<MeetingRequestEntity> requests) {
        GetMeetingRequestsResponseDto responseBody = new GetMeetingRequestsResponseDto(requests);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
