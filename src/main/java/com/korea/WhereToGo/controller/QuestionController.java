package com.korea.WhereToGo.controller;


import com.korea.WhereToGo.dto.request.question.PatchQuestionRequestDto;
import com.korea.WhereToGo.dto.request.question.PostQuestionRequestDto;
import com.korea.WhereToGo.dto.response.question.*;
import com.korea.WhereToGo.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/question")
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping("")
    public ResponseEntity<? super PostQuestionResponseDto> postQuestion(
            @RequestBody @Valid PostQuestionRequestDto requestBody,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PostQuestionResponseDto> response = questionService.postQuestion(requestBody, userId);
        return response;
    }

    @GetMapping("/list")
    public ResponseEntity<? super GetAllQuestionResponseDto> getAllQuestion() {
        ResponseEntity<? super GetAllQuestionResponseDto> response = questionService.getAllQuestions();
        return response;
    }

    @GetMapping("/detail/{questionId}")
    public ResponseEntity<? super GetQuestionResponseDto> getQuestion(
            @PathVariable("questionId") Long questionId
    ) {
        ResponseEntity<? super GetQuestionResponseDto> response = questionService.getQuestion(questionId);
        return response;
    }

    @PatchMapping("/update/{questionId}")
    public ResponseEntity<? super PatchQuestionResponseDto> patchQuestion(
            @RequestBody @Valid PatchQuestionRequestDto requestBody,
            @PathVariable("questionId") Long questionId,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<? super PatchQuestionResponseDto> response = questionService.patchQuestion(requestBody, questionId, userId);
        return response;
    }

    @DeleteMapping("/delete/{questionId}")
    public ResponseEntity<? super DeleteQuestionResponseDto> deleteQuestion(
            @PathVariable("questionId") Long questionId,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super DeleteQuestionResponseDto> response = questionService.deleteQuestion(questionId);
        return response;
    }

}
