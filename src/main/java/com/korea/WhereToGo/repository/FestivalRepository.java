package com.korea.WhereToGo.repository;

import com.korea.WhereToGo.entity.FestivalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FestivalRepository extends JpaRepository<FestivalEntity, Integer> {
}
