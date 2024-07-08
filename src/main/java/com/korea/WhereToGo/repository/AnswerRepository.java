package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
    AnswerEntity findByAnswerId(Long AnswerId);
    @Query(value =
                "SELECT *" +
                        "FROM answer AS A" +
                        "WHERE question_id= :questionId",
            nativeQuery = true
    )
    List<AnswerEntity> getAnswerByQuestionId(Long questionId);
    List<AnswerEntity> findByUserId(String userId);
}
