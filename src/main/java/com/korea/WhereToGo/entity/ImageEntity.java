package com.korea.WhereToGo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "image")
@Table(name = "image")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String contentId;
    private String image;

    @OneToOne
    @JoinColumn(name = "meeting_id")
    private MeetingEntity meeting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "review_id")
    private ReviewEntity review;

    public ImageEntity(String contentId, String image, String userId){
        this.userId = userId;
        this.contentId = contentId;
        this.image = image;
    }

     public Long getReviewId() {
        return review != null ? review.getReviewId() : null;
    }
}

