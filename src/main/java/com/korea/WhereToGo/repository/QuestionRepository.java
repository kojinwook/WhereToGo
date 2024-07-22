package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
//    @Query(
//            value=
//                    "SELECT *" +
//                            "FROM question as B" +
//                            "WHERE question_id = ?1",
//            nativeQuery = true
//    )
//    QuestionEntity getQuestion(Long QuestionId);
    QuestionEntity findByQuestionId(Long QuestionId);
    List<QuestionEntity> findByNickname(String nickname);





}
