package com.korea.WhereToGo.controller;

import com.korea.WhereToGo.dto.request.answer.PatchAnswerRequestDto;
import com.korea.WhereToGo.dto.request.answer.PostAnswerRequestDto;
import com.korea.WhereToGo.dto.response.answer.*;
import com.korea.WhereToGo.service.AnswerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/question/answer")
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("")
    public ResponseEntity<? super PostAnswerResponseDto> postAnswer(
            @RequestBody @Valid PostAnswerRequestDto requestBody
    ){
        System.out.println(requestBody);
        ResponseEntity<? super PostAnswerResponseDto> response = answerService.PostAnswer(requestBody);
        return response;
    }
    @GetMapping("/list/{questionId}")
    public ResponseEntity<? super GetAllAnswerResponseDto> getAllAnswers(
            @PathVariable("questionId") Long questionId
    ){
        ResponseEntity<? super GetAllAnswerResponseDto> response = answerService.getAllAnswers(questionId);
        return response;
    }
    @GetMapping("/detail/{answerId}")
    public  ResponseEntity<? super GetAnswerResponseDto> getAnswer(
            @PathVariable("answerId") Long answerId
    ){
        ResponseEntity<? super GetAnswerResponseDto> response = answerService.getAnswer(answerId);
        return response;
    }
    @PatchMapping("/update/{answerId}")
    public ResponseEntity<? super PatchAnswerResponseDto> patchAnswer(
            @RequestBody @Valid PatchAnswerRequestDto requestBody,
            @PathVariable("answerId") Long answerId
            ){
        ResponseEntity<? super PatchAnswerResponseDto> response = answerService.patchAnswer(requestBody, answerId);
        return response;
    }
    @DeleteMapping("/delete/{answerId}")
    public ResponseEntity<? super DeleteAnswerResponseDto> deleteAnswer(
            @PathVariable("answerId") Long answerId,
            @AuthenticationPrincipal String userId
    ){
        ResponseEntity<? super DeleteAnswerResponseDto> response = answerService.deleteAnswer(answerId);
        return response;
    }






}
