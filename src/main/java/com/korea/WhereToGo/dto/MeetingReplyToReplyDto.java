package com.korea.WhereToGo.dto;

import com.korea.WhereToGo.entity.MeetingReplyToReplyEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
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
