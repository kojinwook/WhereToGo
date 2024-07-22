package com.korea.WhereToGo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.korea.WhereToGo.dto.UserDto;
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

    @Transient
    private UserDto userDto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    @JsonBackReference
    private UserEntity creator;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ImageEntity> imageList = new ArrayList<>();

    @CreatedDate
    private LocalDateTime createDate;
    @LastModifiedDate
    private LocalDateTime modifyDate;

    private int maxParticipants;
    @ElementCollection
    private List<String> tags;
    @ElementCollection
    private List<String> categories;
    @ElementCollection
    private List<String> locations;

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<MeetingBoardEntity> meetingBoardList = new ArrayList<>();

    @OneToMany(mappedBy = "meeting", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<MeetingRequestEntity> participants = new ArrayList<>();

    @ManyToMany(mappedBy = "meeting", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<MeetingUsersEntity> meetingUsers = new ArrayList<>();

    public MeetingEntity(PostMeetingRequestDto dto, UserEntity creator) {
        this.title = dto.getTitle();
        this.introduction = dto.getIntroduction();
        this.content = dto.getContent();
        this.maxParticipants = dto.getMaxParticipants();
        this.tags = dto.getTags();
        this.categories = dto.getCategories();
        this.locations = dto.getLocations();
        this.createDate = LocalDateTime.now();
        this.creator = creator;
    }

    public void patchMeeting(PatchMeetingRequestDto dto){
        this.title = dto.getTitle();
        this.introduction = dto.getIntroduction();
        this.content = dto.getContent();
        this.maxParticipants = dto.getMaxParticipants();
        this.tags = dto.getTags();
        this.categories = dto.getTags();
        this.locations = dto.getLocations();
        this.modifyDate = LocalDateTime.now();
    }
}
