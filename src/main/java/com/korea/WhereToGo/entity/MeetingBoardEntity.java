package com.korea.WhereToGo.entity;

import com.korea.WhereToGo.dto.request.meeting.PostMeetingBoardRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id")
    private MeetingEntity meeting;

    @OneToMany(mappedBy = "meetingBoard", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ImageEntity> imageList = new ArrayList<>();

    public MeetingBoardEntity(PostMeetingBoardRequestDto dto){
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.address = dto.getAddress();
    }
}
