package com.korea.WhereToGo.dto.response.meeting.board;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetMeetingBoardsTitleResponseDto extends ResponseDto {

    private List<String> meetingBoardTitle;

    public GetMeetingBoardsTitleResponseDto(List<String> meetingBoardTitle) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.meetingBoardTitle = meetingBoardTitle;
    }

    public static ResponseEntity<? super GetMeetingBoardsTitleResponseDto> success(List<String> meetingBoardTitle) {
        GetMeetingBoardsTitleResponseDto responseBody = new GetMeetingBoardsTitleResponseDto(meetingBoardTitle);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
