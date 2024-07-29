package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    QuestionEntity findByQuestionId(Long QuestionId);
    List<QuestionEntity> findByNickname(String nickname);





}
