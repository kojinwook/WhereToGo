package com.korea.WhereToGo.service;

import com.korea.WhereToGo.dto.request.Answer.PatchAnswerRequestDto;
import com.korea.WhereToGo.dto.request.Answer.PostAnswerRequestDto;
import com.korea.WhereToGo.dto.request.Question.PatchQuestionRequestDto;
import com.korea.WhereToGo.dto.request.Question.PostQuestionRequestDto;
import com.korea.WhereToGo.dto.response.Answer.*;
import com.korea.WhereToGo.dto.response.Question.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public interface AnswerService {
    ResponseEntity<? super GetAnswerResponseDto> getAnswer(Long AnswerId);
    ResponseEntity<?  super PostAnswerResponseDto> PostAnswer(PostAnswerRequestDto dto);
    ResponseEntity<? super PatchAnswerResponseDto> patchAnswer(PatchAnswerRequestDto dto, Long AnswerId);
    ResponseEntity<? super DeleteAnswerResponseDto> deleteAnswer(Long AnswerId);
    ResponseEntity<? super GetAllAnswerResponseDto> getAllAnswers();
}
