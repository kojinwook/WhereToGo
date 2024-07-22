package com.korea.WhereToGo.dto.response.meeting.board.reply;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.MeetingBoardReplyDto;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.MeetingBoardReplyEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetBoardReplyListResponseDto extends ResponseDto {
    private List<MeetingBoardReplyDto> replyList;

    public GetBoardReplyListResponseDto(List<MeetingBoardReplyEntity> replyEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.replyList = replyEntities.stream()
                .map(MeetingBoardReplyDto::new)
                .collect(Collectors.toList());
    }

    public static ResponseEntity<? super GetBoardReplyListResponseDto> success(List<MeetingBoardReplyEntity> replyEntities) {
        GetBoardReplyListResponseDto responseBody = new GetBoardReplyListResponseDto(replyEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
