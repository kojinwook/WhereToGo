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
import java.util.ArrayList;
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

    private String title;

    private String content;

    private String type;

    private String image;

    private String userId;

    private Boolean answered;

    @CreationTimestamp
    private LocalDateTime createDateTime;

    @UpdateTimestamp
    private LocalDateTime modifyDateTime;

//    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL,orphanRemoval = true)
//    private List<Attachment> attachments = new ArrayList<>();

//    000@JsonManagedReference
//    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL,orphanRemoval = true)
//    private List<AnswerEntity> answers;

    public QuestionEntity(PostQuestionRequestDto dto){
        this.title= dto.getTitle();
        this.content=dto.getContent();
        this.userId = dto.getUserId();
        this.answered = false;
        this.createDateTime = LocalDateTime.now();
        this.type = dto.getType();
        this.image = null;

    }


    public void patchQuestion(PatchQuestionRequestDto dto){
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.type = dto.getType();
        this.image= dto.getImage();
        this.modifyDateTime = LocalDateTime.now();
    }


}
