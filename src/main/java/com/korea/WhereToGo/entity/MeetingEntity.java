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
    private Long id;

    private String title;
    private String introduction;
    private String content;

    @OneToOne(mappedBy = "meeting")
    private ImageEntity image;

    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime modifyDate;

    public MeetingEntity(String userId, String title, String introduction, String content, String meetingImage){
//        this.userId = userId;
        this.title = title;
        this.introduction = introduction;
        this.content = content;
    }

    public MeetingEntity(PostMeetingRequestDto dto) {
        this.title = dto.getTitle();
        this.introduction = dto.getIntroduction();
        this.content = dto.getContent();
        this.createDate = LocalDateTime.now();
    }
}
