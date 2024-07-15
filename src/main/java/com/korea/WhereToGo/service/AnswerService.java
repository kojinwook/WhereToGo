package com.korea.WhereToGo.service;

import com.korea.WhereToGo.dto.request.answer.PatchAnswerRequestDto;
import com.korea.WhereToGo.dto.request.answer.PostAnswerRequestDto;
import com.korea.WhereToGo.dto.response.answer.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public interface AnswerService {
    ResponseEntity<? super GetAnswerResponseDto> getAnswer(Long AnswerId);
    ResponseEntity<?  super PostAnswerResponseDto> PostAnswer(PostAnswerRequestDto dto);
    ResponseEntity<? super PatchAnswerResponseDto> patchAnswer(PatchAnswerRequestDto dto, Long AnswerId);
    ResponseEntity<? super DeleteAnswerResponseDto> deleteAnswer(Long AnswerId);
    ResponseEntity<? super GetAllAnswerResponseDto> getAllAnswers(Long questionId);
}
