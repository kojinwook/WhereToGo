package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.FestivalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FestivalRepository extends JpaRepository<FestivalEntity, Integer> {
    FestivalEntity findByContentId(String ContentId);
    List<FestivalEntity> findByAreaCode(String areaCode);
}
