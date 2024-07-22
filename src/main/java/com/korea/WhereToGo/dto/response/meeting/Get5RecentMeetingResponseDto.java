package com.korea.WhereToGo.dto.response.meeting;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.MeetingEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class Get5RecentMeetingResponseDto extends ResponseDto {

    private List<MeetingEntity> meetingList;

    public Get5RecentMeetingResponseDto(List<MeetingEntity> meetingList) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.meetingList = meetingList;
    }

    public static ResponseEntity<? super Get5RecentMeetingResponseDto> success(List<MeetingEntity> meetingList) {
        Get5RecentMeetingResponseDto responseBody = new Get5RecentMeetingResponseDto(meetingList);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
