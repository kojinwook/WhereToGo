package com.korea.WhereToGo.entity;

import com.korea.WhereToGo.dto.request.meeting.PostMeetingRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "meeting")
@Table(name = "meeting")
public class MeetingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meetingId;

    private String title;
    private String introduction;
    private String content;
    private String userNickname;

    @OneToOne(mappedBy = "meeting", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ImageEntity imageList;

    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime modifyDate;
    private int maxParticipants;

    public MeetingEntity(String userId, String title, String introduction, String content, String userNickname, int maxParticipants) {
        this.title = title;
        this.introduction = introduction;
        this.content = content;
        this.userNickname = userNickname;
        this.maxParticipants = maxParticipants;
    }

    public MeetingEntity(PostMeetingRequestDto dto) {
        this.title = dto.getTitle();
        this.introduction = dto.getIntroduction();
        this.content = dto.getContent();
        this.userNickname = dto.getNickname();
        this.maxParticipants = dto.getMaxParticipants();
        this.createDate = LocalDateTime.now();
    }
}
