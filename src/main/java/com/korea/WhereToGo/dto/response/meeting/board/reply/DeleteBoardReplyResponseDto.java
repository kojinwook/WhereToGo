package com.korea.WhereToGo.dto.response.meeting.board.reply;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class DeleteBoardReplyResponseDto extends ResponseDto {

    public DeleteBoardReplyResponseDto(){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<? super DeleteBoardReplyResponseDto> success(){
        DeleteBoardReplyResponseDto responseBody = new DeleteBoardReplyResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistBoardReply(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_BOARD_REPLY, ResponseMessage.NOT_EXISTED_BOARD_REPLY);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> noPermission(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.DO_NOT_HAVE_PERMISSION, ResponseMessage.DO_NOT_HAVE_PERMISSION);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseBody);
    }
}
