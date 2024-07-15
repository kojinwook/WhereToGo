package com.korea.WhereToGo.service;

import com.korea.WhereToGo.dto.request.question.PatchQuestionRequestDto;
import com.korea.WhereToGo.dto.request.question.PostQuestionRequestDto;
import com.korea.WhereToGo.dto.response.question.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface QuestionService {
    ResponseEntity<? super GetQuestionResponseDto> getQuestion(Long QuestionId);
    ResponseEntity<? super PostQuestionResponseDto> postQuestion(PostQuestionRequestDto dto, String userId);
    ResponseEntity<? super PatchQuestionResponseDto> patchQuestion(PatchQuestionRequestDto dto, Long QuestionId, String userId);
    ResponseEntity<? super DeleteQuestionResponseDto> deleteQuestion(Long QuestionId);
    ResponseEntity<? super GetAllQuestionResponseDto> getAllQuestions();
}
