package com.korea.WhereToGo.dto.response.meeting.board;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.MeetingBoardEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class GetMeetingBoardResponseDto extends ResponseDto {

    private MeetingBoardEntity meetingBoard;

    public GetMeetingBoardResponseDto(MeetingBoardEntity meetingBoard) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.meetingBoard = meetingBoard;
    }

    public static ResponseEntity<? super GetMeetingBoardResponseDto> success(MeetingBoardEntity meetingBoard) {
        GetMeetingBoardResponseDto responseBody = new GetMeetingBoardResponseDto(meetingBoard);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistMeetingBoard() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_MEETING_BOARD, ResponseMessage.NOT_EXISTED_MEETING_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
