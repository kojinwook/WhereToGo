package com.korea.WhereToGo.service.serviceImplement;


import com.korea.WhereToGo.dto.request.Question.PatchQuestionRequestDto;
import com.korea.WhereToGo.dto.request.Question.PostQuestionRequestDto;
import com.korea.WhereToGo.dto.response.Question.*;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.QuestionEntity;
import com.korea.WhereToGo.repository.QuestionRepository;
import com.korea.WhereToGo.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionImplement implements QuestionService {
    private final QuestionRepository questionRepository;

    @Override
    public ResponseEntity<? super GetQuestionResponseDto> getQuestion(Long QuestionId){
        QuestionEntity questionEntity = null;
        try{
         questionEntity = questionRepository.findByQuestionId(QuestionId);
            if(questionEntity == null) return GetQuestionResponseDto.notExistQuestion();
        }catch( Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetQuestionResponseDto.success(questionEntity);
    }
    @Override
    public ResponseEntity<? super PostQuestionResponseDto> postQuestion(PostQuestionRequestDto dto){
        try{
            QuestionEntity questionEntity = new QuestionEntity(dto);
            questionRepository.save(questionEntity);
        }catch( Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostQuestionResponseDto.success();
    }
    @Override
    public ResponseEntity<? super PatchQuestionResponseDto> patchQuestion(PatchQuestionRequestDto dto, Long QuestionId){
        try{
            QuestionEntity questionEntity = questionRepository.findByQuestionId(QuestionId);
            if(questionEntity == null) return PatchQuestionResponseDto.notExistQuestion();

            questionEntity.patchQuestion(dto);
            questionRepository.save(questionEntity);
        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchQuestionResponseDto.success();
    }
    @Override
    public ResponseEntity<? super DeleteQuestionResponseDto> deleteQuestion(Long QuestionId){
        try{
            QuestionEntity questionEntity = questionRepository.findByQuestionId(QuestionId);
            if(questionEntity == null) return DeleteQuestionResponseDto.notExistQuestion();
            questionRepository.delete(questionEntity);
        }catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return DeleteQuestionResponseDto.success();
    }
    @Override
    public ResponseEntity<? super GetAllQuestionResponseDto> getAllQuestions(){
        List<QuestionEntity> questions = null;
        try{
            questions = questionRepository.findAll();
        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetAllQuestionResponseDto.success(questions);
    }




}
