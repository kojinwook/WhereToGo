package com.korea.WhereToGo.service;

import com.korea.WhereToGo.dto.request.Question.PatchQuestionRequestDto;
import com.korea.WhereToGo.dto.request.Question.PostQuestionRequestDto;
import com.korea.WhereToGo.dto.response.Question.*;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface QuestionService {
    ResponseEntity<? super GetQuestionResponseDto> getQuestion(Long QuestionId);
    ResponseEntity<? super PostQuestionResponseDto> postQuestion(PostQuestionRequestDto dto);
    ResponseEntity<? super PatchQuestionResponseDto> patchQuestion(PatchQuestionRequestDto dto, Long QuestionId);
    ResponseEntity<? super DeleteQuestionResponseDto> deleteQuestion(Long QuestionId);
    ResponseEntity<? super GetAllQuestionResponseDto> getAllQuestions();
}
