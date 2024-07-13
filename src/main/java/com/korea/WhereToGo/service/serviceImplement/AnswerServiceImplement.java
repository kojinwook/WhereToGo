package com.korea.WhereToGo.service.serviceImplement;

import com.korea.WhereToGo.dto.request.answer.PatchAnswerRequestDto;
import com.korea.WhereToGo.dto.request.answer.PostAnswerRequestDto;
import com.korea.WhereToGo.dto.response.answer.*;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.entity.AnswerEntity;
import com.korea.WhereToGo.entity.QuestionEntity;
import com.korea.WhereToGo.repository.AnswerRepository;
import com.korea.WhereToGo.repository.QuestionRepository;
import com.korea.WhereToGo.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerServiceImplement implements AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Override
    public ResponseEntity<? super GetAnswerResponseDto> getAnswer(Long answerId){
        AnswerEntity answerEntities = new AnswerEntity();
        try{
            answerEntities= answerRepository.findByAnswerId(answerId);
            if(answerEntities==null) return GetAnswerResponseDto.notExistAnswer();

        }
        catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetAnswerResponseDto.success(answerEntities);
    }
    @Override
    public ResponseEntity<? super PostAnswerResponseDto> PostAnswer(PostAnswerRequestDto dto){
        Long questionId = dto.getQuestionId();
        try{
            QuestionEntity questionEntity = questionRepository.findByQuestionId(questionId);
            if(questionEntity == null) return PostAnswerResponseDto.notExistQuestion();

            AnswerEntity  answerEntity = new AnswerEntity(dto);
            answerRepository.save(answerEntity);
        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostAnswerResponseDto.success();
    }
    @Override
    public ResponseEntity<? super PatchAnswerResponseDto> patchAnswer(PatchAnswerRequestDto dto, Long AnswerId) {
        try {
            AnswerEntity answerEntity = answerRepository.findByAnswerId(AnswerId);
            if (answerEntity == null) return PatchAnswerResponseDto.notExistAnswer();

            answerEntity.patchAnswer(dto);
            answerRepository.save(answerEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchAnswerResponseDto.success();
    }
    @Override
    public ResponseEntity<? super DeleteAnswerResponseDto> deleteAnswer(Long answerId){
        try{
            AnswerEntity answerEntity = answerRepository.findByAnswerId(answerId);
            if(answerEntity == null) return DeleteAnswerResponseDto.notExistedAnswer();
            answerRepository.delete(answerEntity);
        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return DeleteAnswerResponseDto.success();
    }
    @Override
    public ResponseEntity<? super GetAllAnswerResponseDto> getAllAnswers(Long questionId){
        List<AnswerEntity> answers = null;
        try{
            answers = answerRepository.findByQuestionId(questionId);
        } catch(Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetAllAnswerResponseDto.success(answers);
    }






}
