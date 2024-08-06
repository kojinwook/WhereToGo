package com.korea.WhereToGo.dto;

import com.korea.WhereToGo.entity.MeetingBoardReplyEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MeetingBoardReplyDto {
    private Long replyId;
    private String reply;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private Long meetingBoardId;
    private UserDto userDto;
    private List<MeetingReplyToReplyDto> replies;

    public MeetingBoardReplyDto(MeetingBoardReplyEntity entity) {
        this.replyId = entity.getReplyId();
        this.reply = entity.getReply();
        this.createDate = entity.getCreateDate();
        this.modifyDate = entity.getModifyDate();
        this.meetingBoardId = entity.getMeetingBoardId();
        this.userDto = entity.getUser() != null ? new UserDto(entity.getUser()) : null;
        this.replies = entity.getReplies().stream()
                .map(MeetingReplyToReplyDto::new)
                .collect(Collectors.toList());
    }
}
