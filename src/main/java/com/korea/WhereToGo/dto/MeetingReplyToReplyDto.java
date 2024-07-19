package com.korea.WhereToGo.dto;

import com.korea.WhereToGo.entity.MeetingReplyToReplyEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MeetingReplyToReplyDto {
    private Long replyReplyId;
    private String replyReply;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private UserDto userDto;

    public MeetingReplyToReplyDto(MeetingReplyToReplyEntity entity) {
        this.replyReplyId = entity.getReplyReplyId();
        this.replyReply = entity.getReplyReply();
        this.createDate = entity.getCreateDate();
        this.modifyDate = entity.getModifyDate();
        this.userDto = entity.getUser() != null ? new UserDto(entity.getUser()) : null;
    }
}
