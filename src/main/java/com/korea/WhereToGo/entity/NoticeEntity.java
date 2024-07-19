package com.korea.WhereToGo.entity;


import com.korea.WhereToGo.dto.request.notice.PatchNoticeRequestDto;
import com.korea.WhereToGo.dto.request.notice.PostNoticeRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity(name="notice")
@Table(name="notice")
@NoArgsConstructor
@AllArgsConstructor
public class NoticeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeId;

    private String title;

    private String content;

    private String nickname;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime modifyDataTime;

    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ImageEntity> imageList;

    public NoticeEntity(PostNoticeRequestDto dto){
        this.title=dto.getTitle();
        this.content=dto.getContent();
//        this.image=dto.getImage();
        this.nickname= dto.getNickname();
        this.createDateTime=LocalDateTime.now();
    }
    public void patchNotice(PatchNoticeRequestDto dto){
        this.title=dto.getTitle();
        this.content=dto.getContent();
//        this.image=dto.getImage();
        this.modifyDataTime=LocalDateTime.now();
    }


}
