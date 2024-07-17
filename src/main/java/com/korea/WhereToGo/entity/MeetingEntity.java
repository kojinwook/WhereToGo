package com.korea.WhereToGo.entity;

import com.korea.WhereToGo.dto.request.meeting.PatchMeetingRequestDto;
import com.korea.WhereToGo.dto.request.meeting.PostMeetingRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ImageEntity> imageList = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime modifyDate;
    private int maxParticipants;
    private List<String> tags = new ArrayList<>();
    private List<String> areas = new ArrayList<>();

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL)
    private List<MeetingBoardEntity> meetingBoardList = new ArrayList<>();


    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL)
    private List<MeetingRequestEntity> participants = new ArrayList<>();

    public MeetingEntity(PostMeetingRequestDto dto) {
        this.title = dto.getTitle();
        this.introduction = dto.getIntroduction();
        this.content = dto.getContent();
        this.userNickname = dto.getNickname();
        this.maxParticipants = dto.getMaxParticipants();
        this.tags = dto.getTags();
        this.areas = dto.getAreas();
        this.createDate = LocalDateTime.now();
    }

    public void patchMeeting(PatchMeetingRequestDto dto){
        this.title = dto.getTitle();
        this.introduction = dto.getIntroduction();
        this.content = dto.getContent();
        this.maxParticipants = dto.getMaxParticipants();
        this.tags = dto.getTags();
        this.areas = dto.getAreas();
        this.modifyDate = LocalDateTime.now();
    }
}
