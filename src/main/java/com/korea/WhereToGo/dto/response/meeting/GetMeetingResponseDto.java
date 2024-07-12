package com.korea.WhereToGo.dto.response.meeting;

import com.korea.WhereToGo.common.ResponseCode;
import com.korea.WhereToGo.common.ResponseMessage;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.MeetingEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@RequiredArgsConstructor
public class GetMeetingResponseDto extends ResponseDto {
    private int meetingId;
    private String userId;
    private String title;
    private String introduction;
    private String content;
    private String meetingImage;

    private GetMeetingResponseDto(MeetingEntity meetingEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.meetingId = meetingEntity.getId();
//        this.userId = meetingEntity.getUserId();
        this.title = meetingEntity.getTitle();
        this.introduction = meetingEntity.getIntroduction();
        this.content = meetingEntity.getContent();
        this.meetingImage = meetingEntity.getMeetingImage();
    }

    public  static ResponseEntity<GetMeetingResponseDto> success(MeetingEntity meetingEntity){
        GetMeetingResponseDto responseDto = new GetMeetingResponseDto(meetingEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    public static ResponseEntity<ResponseDto> notExistMeeting() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXISTED_MEETING, ResponseMessage.NOT_EXISTED_MEETING);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }
}
