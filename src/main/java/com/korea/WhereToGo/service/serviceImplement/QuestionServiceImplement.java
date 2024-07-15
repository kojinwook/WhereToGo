package com.korea.WhereToGo.service.serviceImplement;


import com.korea.WhereToGo.dto.request.question.PatchQuestionRequestDto;
import com.korea.WhereToGo.dto.request.question.PostQuestionRequestDto;
import com.korea.WhereToGo.dto.response.ResponseDto;
import com.korea.WhereToGo.dto.response.question.*;
import com.korea.WhereToGo.entity.ImageEntity;
import com.korea.WhereToGo.entity.QuestionEntity;
import com.korea.WhereToGo.entity.UserEntity;
import com.korea.WhereToGo.repository.ImageRepository;
import com.korea.WhereToGo.repository.QuestionRepository;
import com.korea.WhereToGo.repository.UserRepository;
import com.korea.WhereToGo.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImplement implements QuestionService {
    private final QuestionRepository questionRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetQuestionResponseDto> getQuestion(Long QuestionId) {
        QuestionEntity questionEntity = null;
        try {
            questionEntity = questionRepository.findByQuestionId(QuestionId);
            if (questionEntity == null) return GetQuestionResponseDto.notExistQuestion();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetQuestionResponseDto.success(questionEntity);
    }

    @Override
    public ResponseEntity<? super PostQuestionResponseDto> postQuestion(PostQuestionRequestDto dto, String userId) {
        try {
            QuestionEntity questionEntity = new QuestionEntity(dto);
            questionRepository.save(questionEntity);

            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null) return PostQuestionResponseDto.notExistUser();

            List<String> imageList = dto.getImageList();
            List<ImageEntity> imageEntities = new ArrayList<>();

            for (String image : imageList) {
                ImageEntity imageEntity = new ImageEntity(image, questionEntity, userId);
                imageEntities.add(imageEntity);
            }
            imageRepository.saveAll(imageEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostQuestionResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchQuestionResponseDto> patchQuestion(PatchQuestionRequestDto dto, Long questionId, String userId) {
        try {
            QuestionEntity questionEntity = questionRepository.findByQuestionId(questionId);
            if (questionEntity == null) return PatchQuestionResponseDto.notExistQuestion();

            questionEntity.patchQuestion(dto);
            questionRepository.save(questionEntity);

            List<String> newImageList = dto.getImageList();

            List<ImageEntity> existingImages = imageRepository.findByQuestion_QuestionId(questionId);

            List<ImageEntity> imageEntities = new ArrayList<>();

            for (ImageEntity existingImage : existingImages) {
                imageRepository.delete(existingImage);
            }

            for (String imageUrl : newImageList) {
                ImageEntity imageEntity = new ImageEntity(imageUrl, questionEntity, userId);
                imageEntities.add(imageEntity);
            }
            imageRepository.saveAll(imageEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchQuestionResponseDto.success();
    }


    @Override
    public ResponseEntity<? super DeleteQuestionResponseDto> deleteQuestion(Long QuestionId) {
        try {
            QuestionEntity questionEntity = questionRepository.findByQuestionId(QuestionId);
            if (questionEntity == null) return DeleteQuestionResponseDto.notExistQuestion();
            questionRepository.delete(questionEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return DeleteQuestionResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetAllQuestionResponseDto> getAllQuestions() {
        List<QuestionEntity> questions = null;
        try {
            questions = questionRepository.findAll();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetAllQuestionResponseDto.success(questions);
    }


}
