package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.RateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RateRepository extends JpaRepository<RateEntity, Integer> {
    List<RateEntity> findByContentId(String contentId);
}
