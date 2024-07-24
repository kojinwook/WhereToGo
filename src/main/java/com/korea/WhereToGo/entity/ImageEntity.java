package com.korea.WhereToGo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Long boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "meeting_id")
    private MeetingEntity meeting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "meeting_board_id")
    private MeetingBoardEntity meetingBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "review_id")
    private ReviewEntity review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "notice_id")
    private NoticeEntity notice;

    public ImageEntity(String contentId, String image, String userId){
        this.userId = userId;
        this.contentId = contentId;
        this.image = image;
    }

    public ImageEntity(String image, QuestionEntity question, String userId) {
        this.image = image;
        this.question = question;
        this.userId = userId;
    }

    public ImageEntity(String image, ReviewEntity review, String userId) {
        this.image = image;
        this.review = review;
        this.userId = userId;
    }

    public ImageEntity(String image, MeetingEntity meeting, String userId) {
        this.image = image;
        this.meeting = meeting;
        this.userId = userId;
    }

    public ImageEntity(String image, MeetingBoardEntity meetingBoard, String userId) {
        this.image = image;
        this.meetingBoard = meetingBoard;
        this.userId = userId;
    }

    public ImageEntity(String image, NoticeEntity notice, String userId){
        this.image = image;
        this.notice = notice;
        this.userId = userId;
    }

    public ImageEntity(String imageUrl, MeetingEntity meetingEntity) {
        this.image = imageUrl;
        this.meeting = meetingEntity;
    }

    public ImageEntity(String imageUrl, NoticeEntity noticeEntity) {
        this.image = imageUrl;
        this.notice = noticeEntity;
    }

    public ImageEntity(String imageUrl, QuestionEntity questionEntity) {
        this.image = imageUrl;
        this.question = questionEntity;
    }

    public Long getReviewId() {
        return review != null ? review.getReviewId() : null;
    }
}

