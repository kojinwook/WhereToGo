package com.korea.WhereToGo.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.korea.WhereToGo.dto.request.Question.PatchQuestionRequestDto;
import com.korea.WhereToGo.dto.request.Question.PostQuestionRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="question")
@Table(name="question")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private String type;

    @NotNull
    private String email;

    @NotNull
    private String userId;

    @NotNull
    private Boolean answered;

    @NotNull
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @NotNull
    @UpdateTimestamp
    private LocalDateTime modifyDateTime;

    @JsonManagedReference
    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<AnswerEntity> answers;

    public QuestionEntity(PostQuestionRequestDto dto){
        this.title= dto.getTitle();
        this.content=dto.getContent();
        this.userId = dto.getUserId();
        this.answered = false;
        this.createDateTime = LocalDateTime.now();
        this.modifyDateTime = LocalDateTime.now();
        this.type = dto.getType();
        this.email = dto.getEmail();
    }

    public void patchQuestion(PatchQuestionRequestDto dto){
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.type = dto.getType();
        this.email = dto.getEmail();
    }


}
