package com.korea.WhereToGo.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.korea.WhereToGo.dto.request.Answer.PatchAnswerRequestDto;
import com.korea.WhereToGo.dto.request.Answer.PostAnswerRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name="answer")
@Table(name="answer")
@NoArgsConstructor
@AllArgsConstructor
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;
    @NotNull
    private String content;
    @NotNull
    private String userId;
    @NotNull
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @NotNull
    @UpdateTimestamp
    private LocalDateTime modifyDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="question_id", nullable=false)
    private QuestionEntity question;

    public AnswerEntity(PostAnswerRequestDto dto){
        this.content=dto.getContent();
        this.userId = dto.getUserId();
        this.createDateTime= LocalDateTime.now();
    }

    public void patchAnswer(PatchAnswerRequestDto dto){
        this.content= dto.getContent();
    }






}
