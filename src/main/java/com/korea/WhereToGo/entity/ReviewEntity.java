package com.korea.WhereToGo.entity;

import com.korea.WhereToGo.dto.request.review.PatchReviewRequestDto;
import com.korea.WhereToGo.dto.request.review.PostReviewRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review")
@Entity(name = "review")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "user_nickname")
    private String nickname;
    @Column(name = "write_datetime")
    private String writeDatetime;
    @Column(name = "modify_datetime")
    private String modifyDatetime;
    private String contentId;
    private String review;
    @Column(name = "rate")
    private int rate;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ImageEntity> imageList = new ArrayList<>();

    public ReviewEntity(PostReviewRequestDto dto, String contentId) {
        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String writeDatetime = simpleDateFormat.format(now);
        this.nickname = dto.getNickname();
        this.writeDatetime = writeDatetime;
        this.contentId = contentId;
        this.review = dto.getReview();
        this.rate = dto.getRate();
    }

    public void PatchReview(PatchReviewRequestDto dto) {
        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String modifyDatetime = simpleDateFormat.format(now);
        this.nickname = dto.getNickname();
        this.modifyDatetime = modifyDatetime;
        this.review = dto.getReview();
        this.rate = dto.getRate();
    }
}
