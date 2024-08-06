package com.korea.WhereToGo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.korea.WhereToGo.dto.UserDto;
import com.korea.WhereToGo.dto.request.meeting.board.reply.PatchBoardReplyRequestDto;
import com.korea.WhereToGo.dto.request.meeting.board.reply.PostBoardReplyRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "meeting_board_reply")
@Table(name = "meeting_board_reply")
public class MeetingBoardReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    private String reply;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private Long meetingBoardId;

    @Transient
    private UserDto userDto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserEntity user;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<MeetingReplyToReplyEntity> replies;

    public MeetingBoardReplyEntity(PostBoardReplyRequestDto dto) {
        this.reply = dto.getReply();
        this.createDate = LocalDateTime.now();
        this.meetingBoardId = dto.getMeetingBoardId();
    }

    public void patchBoardReply(PatchBoardReplyRequestDto dto) {
        this.reply = dto.getReply();
        this.modifyDate = LocalDateTime.now();
    }
}
