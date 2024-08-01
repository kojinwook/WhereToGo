package com.korea.WhereToGo.entity;


import com.korea.WhereToGo.dto.request.answer.PatchAnswerRequestDto;
import com.korea.WhereToGo.dto.request.answer.PostAnswerRequestDto;
import jakarta.persistence.*;
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

    private String content;

    private String nickname;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime modifyDateTime;

    private Long questionId;

    public AnswerEntity(PostAnswerRequestDto dto){
        this.content=dto.getContent();
        this.nickname = dto.getNickname();
        this.questionId = dto.getQuestionId();
        this.createDateTime= LocalDateTime.now();
    }

    public void patchAnswer(PatchAnswerRequestDto dto){
        this.content= dto.getContent();
        this.modifyDateTime = LocalDateTime.now();
    }
}
