package com.korea.WhereToGo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.korea.WhereToGo.dto.UserDto;
import com.korea.WhereToGo.dto.request.meeting.board.PatchMeetingBoardRequestDto;
import com.korea.WhereToGo.dto.request.meeting.board.PostMeetingBoardRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "meeting_board")
@Table(name = "meeting_board")
public class MeetingBoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meetingBoardId;

    private String title;
    private String content;
    private String address;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    @Column(name = "meeting_id", insertable = false, updatable = false)
    private Long meetingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    @Fetch(FetchMode.JOIN)
    @JsonBackReference
    private MeetingEntity meeting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Fetch(FetchMode.JOIN)
    @JsonBackReference
    private UserEntity user;

    @Transient
    private UserDto userDto;

    @OneToMany(mappedBy = "meetingBoard", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    @JsonManagedReference
    private List<ImageEntity> imageList = new ArrayList<>();

    public MeetingBoardEntity(PostMeetingBoardRequestDto dto){
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.address = dto.getAddress();
        this.createDate = LocalDateTime.now();
    }

    public void patchMeetingBoard(PatchMeetingBoardRequestDto dto, String userId){
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.address = dto.getAddress();
        this.modifyDate = LocalDateTime.now();
        this.imageList.clear();
        for (String imageUrl : dto.getImageList()) {
            this.imageList.add(new ImageEntity(imageUrl, this, userId));
        }
    }
}
