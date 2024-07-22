package com.korea.WhereToGo.dto.response.meeting.board;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PatchMeetingBoardResponseDto extends ResponseDto {

    public PatchMeetingBoardResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<? super PatchMeetingBoardResponseDto> success(){
        PatchMeetingBoardResponseDto responseBody = new PatchMeetingBoardResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistMeetingBoard(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_MEETING_BOARD, ResponseMessage.NOT_EXISTED_MEETING_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noPermission(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.DO_NOT_HAVE_PERMISSION, ResponseMessage.DO_NOT_HAVE_PERMISSION);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseBody);
    }
}
